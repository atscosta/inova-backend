package br.jus.cnj.inova.validators.business.sgt.compatibilidade;

import br.jus.cnj.inova.compatibilidadesgt.CompatibilidadeSgtService;
import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.processo.capa.Assunto;
import br.jus.cnj.inova.sgt.SgtClientFacade;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Validator(type = ValidatorType.ASSUNTOS)
@RequiredArgsConstructor
public class CompatibilidadeClasseAssuntoValidator implements ProcessoValidator {

    private final SgtClientFacade sgtClient;
    private final CompatibilidadeSgtService compatibilidadeSgtService;

    @Override
    public ValidationResult validate(Processo processo) {
        try {
            final var codigoClasse = processo.getDadosBasicos().getClasseProcessual();
            final var codigosAssuntos = this.getCodigosAssuntos(processo);

            final var errors = this.getValidationResults(
                    codigoClasse,
                    this.getCodigosAncestraisClasse(codigoClasse),
                    this.getCodigosAncestraisAssuntosMap(codigosAssuntos)).stream()
                    .filter(ValidationResult::isError)
                    .collect(Collectors.toList());

            return errors.isEmpty() ?
                    new ValidationResult() :
                    new ValidationResult(Severity.ERROR, "For identificada a incompatibilidade entre alguns dos " +
                            "assuntos com a classe do processo", errors);
        } catch (Exception ex) {
            return new ValidationResult(Severity.ERROR, "Falha ao tentar verificar a compatibilade entre a" +
                    "classe e os assuntos do processo. Causa: " + ex.getMessage());
        }
    }

    private List<Long> getCodigosAssuntos(Processo processo) {
        return processo.getDadosBasicos().getAssunto().stream()
                .map(Assunto::getCodigoNacional)
                .collect(Collectors.toList());
    }

    private List<Long> getCodigosAncestraisClasse(Long codigoClasse) {
        return this.sgtClient.getStringPaisItem(codigoClasse, TipoItemEnum.CLASSE).stream()
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }

    private Map<Long, Collection<String>> getCodigosAncestraisAssuntosMap(List<Long> codigosAssuntos) {
        final var map = new HashMap<Long, Collection<String>>();
        for (var cod : codigosAssuntos) {
            map.put(cod, this.sgtClient.getStringPaisItem(cod, TipoItemEnum.ASSUNTO));
        }
        return map;
    }

    private ArrayList<ValidationResult> getValidationResults(Long codigoClasse,
                                                             List<Long> codigosAncestraisClasses,
                                                             Map<Long, Collection<String>> codigosAncestraisAssuntos) {
        final var results = new ArrayList<ValidationResult>();
        for (var codClasseAncestral : codigosAncestraisClasses) {
            for (var entry : codigosAncestraisAssuntos.entrySet()) {
                for (var codAssuntoAncestral : entry.getValue()) {
                    final var compativel = this.compatibilidadeSgtService
                            .check(codClasseAncestral, Long.valueOf(codAssuntoAncestral));
                    results.add(compativel ?
                            new ValidationResult() :
                            new ValidationResult(
                                    Severity.ERROR,
                                    String.format("A classe %d, filha da classe %d não é compatível com o assunto %d, " +
                                            "filho do assunto %s", codigoClasse, codClasseAncestral, entry.getKey(), codAssuntoAncestral)
                            ));
                }
            }
        }
        return results;
    }

    @Override
    public String getTitle() {
        return "A classe e os assuntos do processo devem ser compatíveis entre si.";
    }
}

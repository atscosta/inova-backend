package br.jus.cnj.inova.validators.business.sgt.compatibilidade;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.processo.capa.Assunto;
import br.jus.cnj.inova.unidadejudiciaria.UnidadeJudiciaria;
import br.jus.cnj.inova.unidadejudiciaria.UnidadeJudiciariaService;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Validator(type = ValidatorType.ASSUNTOS)
@RequiredArgsConstructor
public class CompatibilidadeAssuntosValidator implements ProcessoValidator {

    private final UnidadeJudiciariaService unidadeJudiciariaService;
    private final CompatibilidadeItemValidator compatibilidadeItemValidator;

    @Override
    public ValidationResult validate(Processo processo) {
        final var codigoOrgao = processo.getDadosBasicos().getOrgaoJulgador().getCodigoOrgao();
        final var unidadeJudiciaria = this.unidadeJudiciariaService.findByCodigo(String.valueOf(codigoOrgao));
        final var errors = getErrors(processo, unidadeJudiciaria);
        return errors.isEmpty() ?
                new ValidationResult() :
                new ValidationResult(Severity.ERROR, "Existem assuntos incompatíveis com a unidade judiciária ou grau do processo", errors);
    }

    private List<ValidationResult> getErrors(Processo processo, UnidadeJudiciaria unidadeJudiciaria) {
        return processo.getDadosBasicos().getAssunto().stream()
                .map(Assunto::getCodigoNacional)
                .filter(Objects::nonNull)
                .map(codigoAssunto -> this.validateSingle(processo, unidadeJudiciaria, codigoAssunto))
                .filter(ValidationResult::isError)
                .collect(Collectors.toList());
    }

    private ValidationResult validateSingle(Processo processo, UnidadeJudiciaria unidadeJudiciaria, Long codigoAssunto) {
        try {
            return this.compatibilidadeItemValidator.validate(
                    unidadeJudiciaria,
                    processo.getGrau(),
                    TipoItemEnum.ASSUNTO,
                    codigoAssunto);
        } catch (Exception ex) {
            return new ValidationResult(Severity.ERROR, "Falha ao tentar validar o assunto: " +
                    codigoAssunto + ". Causa: " + ex.getMessage());
        }
    }

    @Override
    public String getTitle() {
        return "Todos os assuntos do processos devem ser compatíveis com sua unidade judiciária e grau.";
    }
}

package br.jus.cnj.inova.validators.business.sgt.compatibilidade;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.processo.movimento.Movimento;
import br.jus.cnj.inova.processo.movimento.MovimentoNacional;
import br.jus.cnj.inova.unidadejudiciaria.UnidadeJudiciaria;
import br.jus.cnj.inova.unidadejudiciaria.UnidadeJudiciariaService;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Validator(type = ValidatorType.MOVIMENTOS)
@RequiredArgsConstructor
public class CompatibilidadeMovimentosValidator implements ProcessoValidator {

    private final UnidadeJudiciariaService unidadeJudiciariaService;
    private final CompatibilidadeItemValidator compatibilidadeItemValidator;

    @Override
    public ValidationResult validate(Processo processo) {
        final var codigoOrgao = processo.getDadosBasicos().getOrgaoJulgador().getCodigoOrgao();
        final var unidadeJudiciaria = this.unidadeJudiciariaService.findByCodigo(codigoOrgao);
        final var errors = getErrors(processo, unidadeJudiciaria);
        return errors.isEmpty() ?
                new ValidationResult() :
                new ValidationResult(Severity.ERROR, "Existem movimentos incompatíveis com a unidade judiciária ou grau do processo", errors);
    }

    private List<ValidationResult> getErrors(Processo processo, UnidadeJudiciaria unidadeJudiciaria) {
        if (Objects.isNull(processo.getMovimento())) {
            return Collections.singletonList(new ValidationResult(Severity.ERROR,
                    "O processo não possui movimentações."));
        }
        return processo.getMovimento().stream()
                .map(Movimento::getMovimentoNacional)
                .filter(Objects::nonNull)
                .map(MovimentoNacional::getCodigoNacional)
                .map(codigoMovimento -> this.validateSingle(processo, unidadeJudiciaria, codigoMovimento))
                .filter(ValidationResult::isError)
                .collect(Collectors.toList());
    }

    private ValidationResult validateSingle(Processo processo, UnidadeJudiciaria unidadeJudiciaria, Long codigoMovimento) {
        try {
            return this.compatibilidadeItemValidator.validate(
                    unidadeJudiciaria,
                    processo.getGrau(),
                    TipoItemEnum.MOVIMENTO,
                    codigoMovimento);
        } catch (Exception ex) {
            return new ValidationResult(Severity.ERROR, "Falha ao tentar validar o movimento: " +
                    codigoMovimento + ". Causa: " + ex.getMessage());
        }
    }

    @Override
    public String getTitle() {
        return "Todos os movimentos do processos devem ser compatíveis com sua unidade judiciária e grau.";
    }
}

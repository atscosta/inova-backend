package br.jus.cnj.inova.validators.business.sgt.integridade;

import br.jus.cnj.inova.processo.movimento.Movimento;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;
import br.jus.cnj.inova.validators.business.sgt.AbstractMovimentosValidator;
import br.jus.cnj.inova.validators.business.sgt.atividade.AtividadeItemValidator;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import lombok.RequiredArgsConstructor;

@Validator(type = ValidatorType.MOVIMENTOS)
@RequiredArgsConstructor
public class CodigoMovimentoNacionalValidator extends AbstractMovimentosValidator implements ProcessoValidator {

    private final AtividadeItemValidator atividadeValidator;

    @Override
    protected ValidationResult validateMovimento(Movimento movimento) {
        return this.getCodigoMovimentoNacional(movimento)
                .map(codigoMovimentoNacional -> this.atividadeValidator.validate(TipoItemEnum.MOVIMENTO, codigoMovimentoNacional))
                .orElse(new ValidationResult(Severity.ERROR, "O movimento " +
                        movimento.getIdentificadorMovimento() + " não está associado a nenhum movimento nacional"));
    }

    @Override
    public String getTitle() {
        return "Todos os movimentos do processo devem estar associados ao código de algum movimento nacional normatizado.";
    }

    @Override
    protected String getErrorMessage() {
        return "Alguma das movimentações do processo não está associada a um código nacional válido.";
    }
}

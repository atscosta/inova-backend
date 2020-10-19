package br.jus.cnj.inova.validators.field.mandatory;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;

@Validator(type = ValidatorType.FIELD)
public class SiglaTribunalValidator extends CamposObrigatoriosValidator {
    
    @Override
    public ValidationResult validate(Processo processo) {
        final var validationResult = super.validateCampoNulo(
            processo.getSiglaTribunal(), "Sigla Tribunal");
        if (validationResult.isSuccess() && processo.getSiglaTribunal().isBlank()) {
            return new ValidationResult(Severity.ERROR,
                "O campo Sigla Tribunal é de preenchimento obrigatório, mas está vazio.");
        }
        return validationResult;
    }
    
}

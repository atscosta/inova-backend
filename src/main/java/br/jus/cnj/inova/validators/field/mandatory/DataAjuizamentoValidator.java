package br.jus.cnj.inova.validators.field.mandatory;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;

@Validator(type = ValidatorType.FIELD)
public class DataAjuizamentoValidator extends CamposObrigatoriosValidator {
    
    @Override
    public ValidationResult validate(Processo processo) {
        final var validationResult = super.validateCampoNulo(
            processo.getDadosBasicos().getDataAjuizamento(), "Data Ajuizamento");
        
        if (validationResult.isSuccess()) {
            if (processo.getDadosBasicos().getDataAjuizamento().isBlank()) {
                return new ValidationResult(Severity.ERROR,
                    "O campo Data Ajuizamento é de preenchimento obrigatório, mas está vazio.");
            }
            if (isInvalidDateTime(processo.getDadosBasicos().getDataAjuizamento())){
                return new ValidationResult(Severity.ERROR,
                    "O campo Data Ajuizamento não corresponde a uma data/hora válida (yyyyMMddhhmmss)");
            }
        }
        return validationResult;
    }
    
    private boolean isInvalidDateTime(String datetime) {
        return !datetime.matches("([0-9]{4})(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])(2[0-3]|[01][0-9])([0-5][0-9])([0-5][0-9])");
    }
    
}

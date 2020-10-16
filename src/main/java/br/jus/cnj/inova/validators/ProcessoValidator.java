package br.jus.cnj.inova.validators;

import br.jus.cnj.inova.processo.Processo;

public interface ProcessoValidator {
    
    ValidationResult validate(Processo processo);
    
    default ValidatorType getValidatorType() {
        return this.getClass().getAnnotation(Validator.class).type();
    }
}

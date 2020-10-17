package br.jus.cnj.inova.validators;

import br.jus.cnj.inova.processo.Processo;

public interface ProcessoValidator {
    
    ValidationResult validate(Processo processo);

    String getTitle();
    
    String getName();
    
    default ValidatorPriority getValidatorPriority() {
        return this.getClass().getAnnotation(Validator.class).priority();
    }
    
    default ValidatorType getValidatorType() {
        return this.getClass().getAnnotation(Validator.class).type();
    }
    
    default Boolean isEnabled() {
        return this.getClass().getAnnotation(Validator.class).enabled();
    }
}

package br.jus.cnj.inova.validators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Validation {

    private String title;
    private ValidatorType type;
    private ValidationResult result;

    public Validation(ProcessoValidator validator, ValidationResult result) {
        this.title = validator.getTitle();
        this.type = validator.getValidatorType();
        this.result = result;
    }

}

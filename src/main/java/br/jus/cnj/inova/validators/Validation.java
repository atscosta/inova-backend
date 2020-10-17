package br.jus.cnj.inova.validators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
<<<<<<< HEAD
=======
@AllArgsConstructor
>>>>>>> 0f29c97888cd02ce40b3f361411a43c184008c9e
@NoArgsConstructor
public class Validation {

    private String title;
<<<<<<< HEAD
    private ValidatorType type;
    private ValidationResult result;

    public Validation(ProcessoValidator validator, ValidationResult result) {
        this.title = validator.getClass().getName();
        this.type = validator.getValidatorType();
        this.result = result;
    }

=======
    private String type;
    private ValidationResult result;

>>>>>>> 0f29c97888cd02ce40b3f361411a43c184008c9e
}

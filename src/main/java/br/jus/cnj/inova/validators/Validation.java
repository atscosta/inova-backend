package br.jus.cnj.inova.validators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Validation {

    private String title;
    private String type;
    private ValidationResult result;

}

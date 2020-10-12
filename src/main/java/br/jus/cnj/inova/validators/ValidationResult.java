package br.jus.cnj.inova.validators;

import lombok.Data;

@Data
public class ValidationResult {
    private final boolean success;
    private final Severity severity;
    private final String message;
}

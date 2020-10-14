package br.jus.cnj.inova.validators.business;

import lombok.Data;

@Data
public class ValidationResult {
    private final boolean success;
    private final Severity severity;
    private final String message;
    
    public ValidationResult() {
        this.success = true;
        this.severity = null;
        this.message = null;
    }
    
    public ValidationResult(Severity severity, String message) {
        this.success = false;
        this.severity = severity;
        this.message = message;
    }
}

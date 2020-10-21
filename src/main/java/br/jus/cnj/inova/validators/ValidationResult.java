package br.jus.cnj.inova.validators;

import lombok.Data;

import java.util.List;

@Data
public class ValidationResult {
    private boolean success;
    private Severity severity;
    private String message;
    private List<ValidationResult> causedBy;

    public ValidationResult() {
        this(true, null, null, null);
    }

    public ValidationResult(Severity severity, String message) {
        this(false, severity, message, null);
    }

    public ValidationResult(Severity severity, String message, List<ValidationResult> causedBy) {
        this(false, severity, message, causedBy);
    }

    private ValidationResult(boolean success, Severity severity, String message, List<ValidationResult> causedBy) {
        this.success = success;
        this.severity = severity;
        this.message = message;
        this.causedBy = causedBy;
    }

    public boolean isError() {
        return !this.success;
    }
}

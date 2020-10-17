package br.jus.cnj.inova.validators.business.sgt;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.processo.capa.Assunto;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;

import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractAssuntosValidator {

    public ValidationResult validate(Processo processo) {
        final var assuntos = Optional.ofNullable(processo.getDadosBasicos().getAssunto());
        if (assuntos.isEmpty() || assuntos.get().isEmpty()) {
            return new ValidationResult(Severity.ERROR, "O processo não possui assuntos.");
        }
        final var errors = assuntos.get().stream()
                .map(this::validateAssunto)
                .filter(ValidationResult::isError)
                .collect(Collectors.toList());
        return errors.isEmpty() ?
                new ValidationResult() :
                new ValidationResult(Severity.ERROR, getErrorMessage(), errors);
    }

    protected abstract ValidationResult validateAssunto(Assunto assunto);

    protected abstract String getErrorMessage();
}

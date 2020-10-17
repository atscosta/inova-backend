package br.jus.cnj.inova.validators.business.sgt;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.processo.movimento.Movimento;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;

import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractMovimentosValidator {

    public ValidationResult validate(Processo processo) {
        if (Objects.isNull(processo.getMovimento())) {
            return new ValidationResult(Severity.ERROR, "O processo não possui movimentações.");
        }
        final var errors = processo.getMovimento().stream()
                .map(this::validateMovimento)
                .filter(ValidationResult::isError)
                .collect(Collectors.toList());
        return errors.isEmpty() ?
                new ValidationResult() :
                new ValidationResult(Severity.ERROR, this.getErrorMessage(), errors);
    }

    protected abstract ValidationResult validateMovimento(Movimento movimento);

    protected abstract String getErrorMessage();
}

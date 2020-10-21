package br.jus.cnj.inova.validators.business.sgt;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.processo.movimento.Movimento;
import br.jus.cnj.inova.processo.movimento.MovimentoLocal;
import br.jus.cnj.inova.processo.movimento.MovimentoNacional;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractMovimentosValidator {

    public ValidationResult validate(Processo processo) {
        return Optional.ofNullable(processo.getMovimento())
                .filter(movimentosList -> !movimentosList.isEmpty())
                .map(this::mapResult)
                .orElse(new ValidationResult(Severity.ERROR, "O processo não possui movimentações."));
    }

    protected ValidationResult mapResult(List<Movimento> movimentosList) {
        final var errors = this.getErrors(movimentosList);
        return errors.isEmpty() ?
                new ValidationResult() :
                new ValidationResult(Severity.ERROR, this.getErrorMessage(), errors);
    }

    protected List<ValidationResult> getErrors(List<Movimento> movimentosList) {
        return movimentosList.stream()
                .map(this::validateMovimento)
                .filter(ValidationResult::isError)
                .collect(Collectors.toList());
    }

    protected Optional<Long> getCodigoMovimentoNacional(Movimento movimento) {
        return Optional.ofNullable(movimento.getMovimentoNacional())
                .map(MovimentoNacional::getCodigoNacional)
                .or(() -> Optional.ofNullable(movimento.getMovimentoLocal())
                        .map(MovimentoLocal::getCodigoPaiNacional));
    }

    protected abstract ValidationResult validateMovimento(Movimento movimento);

    protected abstract String getErrorMessage();
}

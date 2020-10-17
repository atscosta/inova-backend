package br.jus.cnj.inova.validators.business.sgt.integridade;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

@Validator(type = ValidatorType.MOVIMENTOS)
public class ProcessoPossuiMovimentacoesValidator implements ProcessoValidator {

    @Override
    public ValidationResult validate(Processo processo) {
        return Optional.ofNullable(processo.getMovimento())
                .filter(Predicate.not(Collection::isEmpty))
                .map(movimentos -> new ValidationResult())
                .orElse(new ValidationResult(Severity.ERROR, "O processo não possui movimentações."));
    }

    @Override
    public String getTitle() {
        return "O processo deve possuir mais de uma movimentação";
    }
}

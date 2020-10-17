package br.jus.cnj.inova.validators.business.sgt.integridade;

import br.jus.cnj.inova.processo.movimento.Movimento;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;
import br.jus.cnj.inova.validators.business.sgt.AbstractMovimentosValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Validator(type = ValidatorType.MOVIMENTOS)
public class DataMovimentoValidator extends AbstractMovimentosValidator implements ProcessoValidator {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Override
    protected ValidationResult validateMovimento(Movimento movimento) {
        try {
            final var date = LocalDate.parse(movimento.getDataHora(), FORMATTER);
            return date.isAfter(LocalDate.now()) ?
                    new ValidationResult(Severity.ERROR, String.format("Data inválida da movimentação: %s. Data: %s.",
                            movimento.getIdentificadorMovimento(), movimento.getDataHora())) :
                    new ValidationResult();
        } catch (Exception ex) {
            return new ValidationResult(Severity.ERROR, "Data inválida da movimentação: " +
                    movimento.getIdentificadorMovimento());
        }
    }

    @Override
    public String getTitle() {
        return "A data de todos os movimentos deve ser uma data válida e anterior à data atual.";
    }
    
    @Override
    public String getName() {
        return "DataMovimento";
    }
    
    @Override
    protected String getErrorMessage() {
        return "Alguma das movimentações do processo possui data inválida.";
    }
}

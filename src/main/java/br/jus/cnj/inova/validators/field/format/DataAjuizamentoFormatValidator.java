package br.jus.cnj.inova.validators.field.format;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Validator(type = ValidatorType.FIELD)
public class DataAjuizamentoFormatValidator implements ProcessoValidator {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Override
    public ValidationResult validate(Processo processo) {
        try {
            final var dataAjuizamento = Optional.ofNullable(processo.getDadosBasicos().getDataAjuizamento())
                    .map(data -> LocalDate.parse(data, FORMATTER))
                    .orElse(null);
            return new ValidationResult();
        } catch (Exception ex) {
            return new ValidationResult(Severity.ERROR, "Data de ajuizamento inválida");
        }
    }

    @Override
    public String getTitle() {
        return "A data de ajuizamento do processo deve ser válida.";
    }
}

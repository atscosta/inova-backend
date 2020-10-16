package br.jus.cnj.inova.validators.field;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.ValidatorType;

@Validator(type = ValidatorType.FIELD)
public class CamposObrigatoriosValidator implements ProcessoValidator {

    @Override
    public ValidationResult validate(Processo processo) {
        return new ValidationResult();
    }
}

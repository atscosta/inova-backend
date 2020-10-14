package br.jus.cnj.inova.validators.business;

import br.jus.cnj.inova.processo.Processo;

@Validator(type = ValidatorType.CLASSES)
public class SegundaValidacao implements ProcessoValidator {

    @Override
    public ValidationResult validate(Processo processo) {
        return null;
    }
}

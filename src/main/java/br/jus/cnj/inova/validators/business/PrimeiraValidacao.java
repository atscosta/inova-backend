package br.jus.cnj.inova.validators.business;

import br.jus.cnj.inova.processo.Processo;

@Validator(type = ValidatorType.UNIDADE_JUDICIARIA)
public class PrimeiraValidacao implements ProcessoValidator {

    @Override
    public ValidationResult validate(Processo processo) {
        return null;
    }
}

package br.jus.cnj.inova.validators.business;

import br.jus.cnj.inova.processo.Processo;

@Validator
public class CamposObrigatoriosValidacao implements ProcessoValidator {
    
    @Override
    public ValidationResult validate(Processo processo) {
        return new ValidationResult();
    }
}

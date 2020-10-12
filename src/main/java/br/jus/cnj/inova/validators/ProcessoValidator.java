package br.jus.cnj.inova.validators;

import br.jus.cnj.inova.processo.Processo;

public interface ProcessoValidator {

    ValidationResult validate(Processo processo);
}

package br.jus.cnj.inova.validators.field.mandatory;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;

@Validator(type = ValidatorType.FIELD)
public class OrgaoJulgadorValidator extends CamposObrigatoriosValidator {
    
    @Override
    public ValidationResult validate(Processo processo) {
        final var validationResult = super.validate(processo,
            processo.getDadosBasicos().getOrgaoJulgador(), "Órgão Julgador");
        
        if (validationResult.isSuccess() && processo.getDadosBasicos().getOrgaoJulgador().isVazio()) {
            return new ValidationResult(Severity.ERROR,
                "O campo Órgão Julgador é de preenchimento obrigatório, mas está vazio.");
        }
        return validationResult;
    }
    
}

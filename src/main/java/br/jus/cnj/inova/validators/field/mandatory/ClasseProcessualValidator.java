package br.jus.cnj.inova.validators.field.mandatory;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;

@Validator(type = ValidatorType.FIELD)
public class ClasseProcessualValidator extends CamposObrigatoriosValidator {
    
    @Override
    public ValidationResult validate(Processo processo) {
        final var validationResult = super.validateCampoNulo(
            processo.getDadosBasicos().getClasseProcessual(), "Classe Processual");
        
        if (validationResult.isSuccess() && processo.getDadosBasicos().getClasseProcessual() == 0) {
            return new ValidationResult(Severity.ERROR,
                "O campo Classe Processual é de preenchimento obrigatório, mas está vazio.");
        }
        return validationResult;
    }

    @Override
    public String getTitle() {
        return "O campo 'classe processual' deve estar preenchido.";
    }
    
}

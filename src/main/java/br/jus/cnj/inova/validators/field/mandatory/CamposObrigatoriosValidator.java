package br.jus.cnj.inova.validators.field.mandatory;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorPriority;
import br.jus.cnj.inova.validators.ValidatorType;

@Validator(type = ValidatorType.FIELD, priority = ValidatorPriority.HIGH)
public class CamposObrigatoriosValidator implements ProcessoValidator {
    
    protected ValidationResult validate(Processo processo, Object campo, String nomeCampo) {
        final var validationResult = this.validate(processo);
        if (validationResult.isSuccess() && campo == null){
            return new ValidationResult(Severity.ERROR,
                String.format("O campo %s é de preenchimento obrigatório, mas não foi informado", nomeCampo));
        }
        return validationResult;
    }
    
    @Override
    public ValidationResult validate(Processo processo) {
        if (processo.getDadosBasicos() == null || processo.getDadosBasicos().isVazio()) {
            return new ValidationResult(Severity.ERROR,
                "O campo Dados Basicos do processo é de preenchimento obrigatório, mas não foi informado");
        }
        return new ValidationResult();
    }
    
    @Override
    public ValidatorType getValidatorType() {
        return ValidatorType.FIELD;
    }

    @Override
    public String getTitle() {
        return "Campos obrigatórios devem estar preenchidos.";
    }
    
    @Override
    public String getName() {
        return "CamposObrigatorios";
    }
}

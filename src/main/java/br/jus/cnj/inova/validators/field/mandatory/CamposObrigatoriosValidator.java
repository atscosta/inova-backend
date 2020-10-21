package br.jus.cnj.inova.validators.field.mandatory;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;
import org.jetbrains.annotations.Nullable;

@Validator(type = ValidatorType.FIELD)
public class CamposObrigatoriosValidator implements ProcessoValidator {
    
    protected ValidationResult validateCampoNulo(@Nullable Object campo, String nomeCampo) {
        if (campo == null){
            return new ValidationResult(Severity.ERROR,
                String.format("O campo %s é de preenchimento obrigatório, mas não foi informado", nomeCampo));
        }
        return new ValidationResult();
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

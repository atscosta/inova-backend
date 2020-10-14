package br.jus.cnj.inova.validators.field.npu;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.ValidatorType;

@Validator(type = ValidatorType.FIELD)
public class NPUValidator implements ProcessoValidator {

    @Override
    public ValidationResult validate(Processo processo) {
        final var numeroProcesso = processo.getDadosBasicos().getNumero();
        try {
            NPU.validate(numeroProcesso);
        } catch (NPUInvalidoException ex) {
            return new ValidationResult(Severity.ERROR, ex.getMessage());
        } catch (Exception ex) {
            return new ValidationResult(Severity.ERROR, "Número do processo inválido: " + numeroProcesso);
        }
        return new ValidationResult();
    }
}

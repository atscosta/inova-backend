package br.jus.cnj.inova.validators.field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

/**
 * Resolução 16 CNJ https://www.conjur.com.br/dl/resolucao-65-cnj.pdf
 *
 * NNNNNNN-DD.AAAA.JTR.OOOO
 */
public class NumeroProcessoValidator implements ConstraintValidator<NumeroProcessoConstraint, String> {
    
    private static final String NPU_PATTERN = "\\d{7}-\\d{2}.\\d{4}.\\d{3}.\\d{4}";
    
    public void initialize(NumeroProcessoConstraint constraint) {
    }
    
    public boolean isValid(String numeroProcesso, ConstraintValidatorContext context) {
        return StringUtils.isNotBlank(numeroProcesso) && numeroProcesso.matches(NPU_PATTERN)
            && validNPU(numeroProcesso);
    }
    
    private boolean validNPU(String numeroProcesso) {
        return NPU.isValid(numeroProcesso);
    }
}

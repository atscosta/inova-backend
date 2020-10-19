package br.jus.cnj.inova.validators.business.sgt.permissao;

import br.jus.cnj.inova.processo.Grau;
import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;

import java.util.Optional;

@Validator(type = ValidatorType.UNIDADE_JUDICIARIA)
public class ValorDaCausaJuizadoValidator implements ProcessoValidator {

    public static final double LIMITE = 40156d;

    @Override
    public ValidationResult validate(Processo processo) {
        if (processo.getGrau() != Grau.JE) {
            return new ValidationResult();
        }

        final var valorDaCausa = Optional.ofNullable(processo.getDadosBasicos().getValorCausa())
                .orElse(0d);

        return valorDaCausa < LIMITE ?
                new ValidationResult() :
                new ValidationResult(Severity.ERROR, "O valor da causa do processo é de " + valorDaCausa +
                        " e excede o limite máximo dos juizados especiais, que é de " + LIMITE);
    }

    @Override
    public String getTitle() {
        return "O valor da causa em processos tramitando em juizados especiais não pode exceder R$ " + LIMITE;
    }
}

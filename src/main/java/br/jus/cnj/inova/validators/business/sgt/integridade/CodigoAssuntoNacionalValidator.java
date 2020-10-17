package br.jus.cnj.inova.validators.business.sgt.integridade;

import br.jus.cnj.inova.processo.capa.Assunto;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;
import br.jus.cnj.inova.validators.business.sgt.AbstractAssuntosValidator;
import br.jus.cnj.inova.validators.business.sgt.atividade.AtividadeItemValidator;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Validator(type = ValidatorType.ASSUNTOS)
@RequiredArgsConstructor
public class CodigoAssuntoNacionalValidator extends AbstractAssuntosValidator implements ProcessoValidator {

    private final AtividadeItemValidator atividadeValidator;

    @Override
    protected ValidationResult validateAssunto(Assunto assunto) {
        return Optional.ofNullable(assunto.getCodigoNacional())
                .map(codigoMovimentoNacional -> this.atividadeValidator.validate(TipoItemEnum.ASSUNTO, codigoMovimentoNacional))
                .orElse(new ValidationResult(Severity.ERROR, String.format("O assunto %d não está associado a nenhum " +
                        "assunto nacional", assunto.getCodigoNacional())));
    }

    @Override
    protected String getErrorMessage() {
        return "Algum dos assuntos do processo não está associada a um assunto nacional válido.";
    }

    @Override
    public String getTitle() {
        return "Todos os assuntos do processo devem estar associados ao código de algum assunto nacional normatizado.";
    }
}

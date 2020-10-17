package br.jus.cnj.inova.validators.business.sgt.permissao;

import br.jus.cnj.inova.processo.capa.Assunto;
import br.jus.cnj.inova.sgt.SgtClientFacade;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;
import br.jus.cnj.inova.validators.business.sgt.AbstractAssuntosValidator;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import lombok.RequiredArgsConstructor;

@Validator(type = ValidatorType.ASSUNTOS)
@RequiredArgsConstructor
public class AssuntoNivelPermitidoValidator extends AbstractAssuntosValidator implements ProcessoValidator {

    public static final int NIVEL_MINIMO = 4;

    private final SgtClientFacade sgtClient;

    @Override
    protected ValidationResult validateAssunto(Assunto assunto) {
        final var nivel = this.sgtClient.getNivel(assunto.getCodigoNacional(), TipoItemEnum.ASSUNTO);
        return nivel >= NIVEL_MINIMO ?
                new ValidationResult() :
                new ValidationResult(Severity.ERROR, String.format("O assunto %d não está em um nível hierárquico" +
                        " válido para ser utilizado", assunto.getCodigoNacional()));
    }

    @Override
    public String getTitle() {
        return "Todos os assuntos do processo devem estar localizados a partir do " + NIVEL_MINIMO + "º nível na hierarquia.";
    }

    @Override
    protected String getErrorMessage() {
        return "Algum dos assuntos do processo não está em um nível permitido para utilização na hierarquia.";
    }
}

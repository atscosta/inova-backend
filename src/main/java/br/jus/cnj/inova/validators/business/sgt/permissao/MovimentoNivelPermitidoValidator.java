package br.jus.cnj.inova.validators.business.sgt.permissao;

import br.jus.cnj.inova.processo.movimento.Movimento;
import br.jus.cnj.inova.sgt.SgtClientFacade;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;
import br.jus.cnj.inova.validators.business.sgt.AbstractMovimentosValidator;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import lombok.RequiredArgsConstructor;

@Validator(type = ValidatorType.MOVIMENTOS)
@RequiredArgsConstructor
public class MovimentoNivelPermitidoValidator extends AbstractMovimentosValidator implements ProcessoValidator {

    private final SgtClientFacade sgtClient;

    @Override
    protected ValidationResult validateMovimento(Movimento movimento) {
        final var codigoMovimentoNacional = this.getCodigoMovimentoNacional(movimento);
        if (codigoMovimentoNacional.isEmpty()) {
            return new ValidationResult(Severity.ERROR, "Não foi possível obter o código nacional do movimento " +
                    movimento.getIdentificadorMovimento());
        } else {
            return this.sgtClient.isUltimoNivel(codigoMovimentoNacional.get(), TipoItemEnum.MOVIMENTO) ?
                    new ValidationResult() :
                    new ValidationResult(Severity.ERROR, String.format("O movimento %d não está em um nível hierárquico" +
                            " válido para ser utilizado", codigoMovimentoNacional.get()));
        }
    }

    @Override
    public String getTitle() {
        return "Todos os movimentos do processo devem estar localizados no último nível na hierarquia.";
    }
    
    @Override
    public String getName() {
        return "MovimentoNivelPermitido";
    }
    
    @Override
    protected String getErrorMessage() {
        return "Algum dos movimentos do processo não está em um nível permitido para utilização na hierarquia.";
    }
}

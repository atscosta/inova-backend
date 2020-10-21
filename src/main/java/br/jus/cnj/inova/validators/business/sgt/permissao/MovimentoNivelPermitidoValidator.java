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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validator(type = ValidatorType.MOVIMENTOS)
@RequiredArgsConstructor
public class MovimentoNivelPermitidoValidator extends AbstractMovimentosValidator implements ProcessoValidator {

    private final SgtClientFacade sgtClient;

    @Override
    protected List<ValidationResult> getErrors(List<Movimento> movimentosList) {
        return movimentosList.stream()
                .map(this::getCodigoMovimentoNacional)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .distinct()
                .map(this::mapValidationResult)
                .collect(Collectors.toList());
    }

    @Override
    protected ValidationResult validateMovimento(Movimento movimento) {
        throw new UnsupportedOperationException();
    }

    private ValidationResult mapValidationResult(Long codigoMovimentoNacional) {
        return this.sgtClient.isUltimoNivel(codigoMovimentoNacional, TipoItemEnum.MOVIMENTO) ?
                new ValidationResult() :
                new ValidationResult(Severity.ERROR,
                        String.format("O movimento %d não está em um nível hierárquico válido para ser utilizado",
                                codigoMovimentoNacional));
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

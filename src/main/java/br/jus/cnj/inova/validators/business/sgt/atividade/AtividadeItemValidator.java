package br.jus.cnj.inova.validators.business.sgt.atividade;

import br.jus.cnj.inova.sgt.SgtClientFacade;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtividadeItemValidator {

    private final SgtClientFacade sgtClient;

    public ValidationResult validate(TipoItemEnum tipoItem, Long seqItem) {
        try {
            final var detalhesItem = this.sgtClient.getArrayDetalhesItem(seqItem, tipoItem);
            return detalhesItem.get("situacao").equals("A") ?
                    new ValidationResult() :
                    new ValidationResult(Severity.WARNING, String.format("O item '%s - %d' não está mais ativo.", tipoItem, seqItem));
        } catch (Exception ex) {
            return new ValidationResult(Severity.WARNING, String.format("Não foi possível verificar se o item '%s - %d' " +
                    "está ativo.", tipoItem, seqItem));
        }
    }
}

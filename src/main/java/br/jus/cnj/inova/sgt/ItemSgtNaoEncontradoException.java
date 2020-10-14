package br.jus.cnj.inova.sgt;

import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;

public class ItemSgtNaoEncontradoException extends RuntimeException {

    private final TipoItemEnum tipoItem;
    private final Long seqItem;

    public ItemSgtNaoEncontradoException(TipoItemEnum tipoItem, Long seqItem) {
        super("Item SGT não encontrado. Tipo: " + tipoItem.getDescricao() + ", Código: " + seqItem);
        this.tipoItem = tipoItem;
        this.seqItem = seqItem;
    }
}

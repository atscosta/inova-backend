package br.jus.cnj.inova.sgt;

import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.ItemSgt;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class LocalItemSgt implements Serializable {
    private final Integer codItem;
    private final Integer codItemPai;
    private final String nome;
    private final String dscGlossario;

    public LocalItemSgt(ItemSgt item) {
        this.codItem = item.getCodItem();
        this.codItemPai = item.getCodItemPai();
        this.nome = item.getNome();
        this.dscGlossario = item.getDscGlossario();
    }
}

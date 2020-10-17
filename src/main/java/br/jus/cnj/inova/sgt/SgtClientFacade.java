package br.jus.cnj.inova.sgt;

import br.jus.tjpb.libs.sgtsoapcient.SgtSoapClient;
import br.jus.tjpb.libs.sgtsoapcient.getarrayfilhositem.ArvoreGenerica;
import br.jus.tjpb.libs.sgtsoapcient.getcomplementomovimento.ComplementoMovimento;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.ItemSgt;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoPesquisaEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class SgtClientFacade {

    private final SgtSoapClient client;

    @Cacheable(value = "arrayDetalhesItemCache")
    public Map<String, String> getArrayDetalhesItem(Long seqItem, TipoItemEnum tipoItem) {
        final var arrayDetalhesItem = this.client.getArrayDetalhesItem(seqItem, tipoItem);
        return Optional.ofNullable(arrayDetalhesItem.get("tipo_item"))
                .map(tipo -> arrayDetalhesItem)
                .orElseThrow(() -> new ItemSgtNaoEncontradoException(tipoItem, seqItem));
    }

    @Cacheable(value = "getArrayFilhosItemCache")
    public Collection<ArvoreGenerica> getArrayFilhosItem(Long seqItem, TipoItemEnum tipoItem) {
        return this.client.getArrayFilhosItem(seqItem, tipoItem);
    }

    @Cacheable(value = "getStringPaisItemCache")
    public Collection<String> getStringPaisItem(Long seqItem, TipoItemEnum tipoItem) {
        return this.client.getStringPaisItem(seqItem, tipoItem);
    }

    @Cacheable(value = "pesquisarItemCache")
    public Collection<ItemSgt> pesquisarItem(TipoItemEnum tipoItem, TipoPesquisaEnum tipoPesquisa, String valorPesquisa) {
        return this.client.pesquisarItem(tipoItem, tipoPesquisa, valorPesquisa);
    }

    @Cacheable(value = "getItemCache")
    public ItemSgt getItem(Long seqItem, TipoItemEnum tipoItem) {
        final var found = this.client.pesquisarItem(tipoItem, TipoPesquisaEnum.CODIGO, String.valueOf(seqItem));
        if (found.isEmpty()) {
            throw new ItemSgtNaoEncontradoException(tipoItem, seqItem);
        } else {
            return found.iterator().next();
        }
    }

    @Cacheable(value = "getComplementoMovimentoCache")
    public Collection<ComplementoMovimento> getComplementoMovimento(Long codMovimento) {
        return this.client.getComplementoMovimento(codMovimento);
    }

    public int getNivel(Long seqItem, TipoItemEnum tipoItem) {
        return this.getStringPaisItem(seqItem, tipoItem).size() + 1;
    }

    public boolean isUltimoNivel(Long seqItem, TipoItemEnum tipoItem) {
        return this.getArrayFilhosItem(seqItem, tipoItem).isEmpty();
    }

    public LocalDate getDataUltimaVersao() {
        return this.client.getDataUltimaVersao();
    }
}

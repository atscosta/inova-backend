package br.jus.cnj.inova.sgt;

import br.jus.tjpb.libs.sgtsoapcient.SgtSoapClient;
import br.jus.tjpb.libs.sgtsoapcient.getarrayfilhositem.ArvoreGenerica;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.ItemSgt;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoPesquisaEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Collection;
import java.util.Map;

@Service
public class ReactiveSgtClient {

    private final SgtSoapClient client;
    private final Long cacheTtl;

    public ReactiveSgtClient(SgtSoapClient client, @Value("${sgt-client.cache-ttl}") Long cacheTtl) {
        this.client = client;
        this.cacheTtl = cacheTtl;
    }

    public Flux<Map<String, String>> getArrayDetalhesItem(Long seqItem, TipoItemEnum tipoItem) {
        return Flux.just(this.client.getArrayDetalhesItem(seqItem, tipoItem))
                .cache(Duration.ofSeconds(this.cacheTtl));
    }

    public Flux<Collection<ArvoreGenerica>> getArrayFilhosItem(Long seqItem, TipoItemEnum tipoItem) {
        return Flux.just(this.client.getArrayFilhosItem(seqItem, tipoItem))
                .cache(Duration.ofSeconds(this.cacheTtl));
    }

    public Flux<Collection<String>> getStringPaisItem(Long seqItem, TipoItemEnum tipoItem) {
        return Flux.just(this.client.getStringPaisItem(seqItem, tipoItem))
                .cache(Duration.ofSeconds(this.cacheTtl));
    }

    public Flux<Collection<ItemSgt>> pesquisarItem(TipoItemEnum tipoItem, TipoPesquisaEnum tipoPesquisa, String valorPesquisa) {
        return Flux.just(this.client.pesquisarItem(tipoItem, tipoPesquisa, valorPesquisa))
                .cache(Duration.ofSeconds(this.cacheTtl));
    }
}

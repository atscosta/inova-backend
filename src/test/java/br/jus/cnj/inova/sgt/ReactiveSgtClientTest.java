package br.jus.cnj.inova.sgt;

import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReactiveSgtClientTest {

    @Autowired
    private ReactiveSgtClient sgtClient;

    @Test
    void getArrayDetalhesItem() {
        final var found = this.sgtClient.getArrayDetalhesItem(999999L, TipoItemEnum.CLASSE)
                .doOnError(error -> System.out.println(">>> Erro: " + error))
                .subscribe(result -> System.out.println(">>> " + result + " " + result.size()));
    }
}

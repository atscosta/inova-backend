package br.jus.cnj.inova.unidadejudiciaria;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
class  UnidadeJudiciariaRepositoryTest {

    @Autowired
    private UnidadeJudiciariaRepository repository;

    @Test
    void findByCodigo() {
        final var found = this.repository.findByCodigo("33818").map(UnidadeJudiciaria::getCodigo);
        StepVerifier.create(found.log())
                .expectNext("33818")
                .verifyComplete();
    }

    @Test
    void findByUf() {
        final var found = this.repository.findAllByUf("PB");
        Assertions.assertNotNull(found);
    }
}

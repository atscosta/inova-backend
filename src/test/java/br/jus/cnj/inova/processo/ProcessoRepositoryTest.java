package br.jus.cnj.inova.processo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProcessoRepositoryTest {

    @Autowired
    private ProcessoRepository repository;

    @Test
    void findBySiglaAndGrauAndNumero() {
        final var found = this.repository.findBySiglaAndGrauAndNumero("TRT1", "G1", "00000291920105010205");

        StepVerifier.create(found)
                .expectNextCount(1)
                .verifyComplete();
    }
}

package br.jus.cnj.inova.tribunal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class TribunalServiceTest {

    @Autowired
    TribunalService tribunalService;

    @Test
    void findAll() {
    }

    @Test
    void findByCodigoJustica() {
    }

    @Test
    void findByUf() {
    }

    @Test
    void findBySigla() {

        final var tribunalMono = tribunalService.findBySigla(Mono.just("TRT13"))
                .map(Tribunal::getId);

        StepVerifier.create(tribunalMono.log())
                .expectNext("5f89e5e165812f12e82d85cf")
                .verifyComplete();

    }
}
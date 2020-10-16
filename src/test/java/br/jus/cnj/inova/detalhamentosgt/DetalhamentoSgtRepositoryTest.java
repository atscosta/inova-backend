package br.jus.cnj.inova.detalhamentosgt;

import br.jus.cnj.inova.processo.Grau;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DetalhamentoSgtRepositoryTest {

    @Autowired
    private DetalhamentoSgtRepository repository;

    @Test
    void findByCodigoJustica() {
        final var found = this.repository.findByCodigoJustica("2674801600a0f60e49a22c20c5992f92");
        assertNotNull(found);
    }

    @Test
    void findByCodigoJusticaAndGrau() {
        final var found = this.repository.findByCodigoJusticaAndGrau("2674801600a0f60e49a22c20c5992f92", Grau.G1);
        assertNotNull(found);
    }
}

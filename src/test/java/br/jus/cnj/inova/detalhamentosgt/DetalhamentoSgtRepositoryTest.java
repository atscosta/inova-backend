package br.jus.cnj.inova.detalhamentosgt;

import br.jus.cnj.inova.processo.Grau;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DetalhamentoSgtRepositoryTest {

    @Autowired
    private DetalhamentoSgtRepository repository;

    @Test
    void findByJusticaAndGrau() {
        final var found = this.repository.findByCodigoJusticaAndGrau("2674801600a0f60e49a22c20c5992f92", Grau.G1);
        assertNotNull(found);
    }
}

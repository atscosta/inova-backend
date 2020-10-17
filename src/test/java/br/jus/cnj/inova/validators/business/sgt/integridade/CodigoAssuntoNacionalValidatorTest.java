package br.jus.cnj.inova.validators.business.sgt.integridade;

import br.jus.cnj.inova.processo.ProcessoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CodigoAssuntoNacionalValidatorTest {

    @Autowired
    private CodigoAssuntoNacionalValidator validator;

    @Autowired
    private ProcessoService processoService;

    @Test
    void validate() {
        final var processo = this.processoService.findById("5f820eb90020f12c21db0377").block();
        final var resultado = this.validator.validate(processo);
        assertNotNull(resultado);
    }
}

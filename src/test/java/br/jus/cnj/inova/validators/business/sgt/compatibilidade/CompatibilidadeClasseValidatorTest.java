package br.jus.cnj.inova.validators.business.sgt.compatibilidade;

import br.jus.cnj.inova.processo.ProcessoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CompatibilidadeClasseValidatorTest {

    @Autowired
    private CompatibilidadeClasseValidator validator;

    @Autowired
    private ProcessoService processoService;

    @Test
    void validate() {
        final var processo = this.processoService.findById("5f820f110020f12c21dbeecb").block();
        final var resultado = this.validator.validate(processo);
        assertNotNull(resultado);
    }
}

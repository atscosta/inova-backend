package br.jus.cnj.inova.validators.business.sgt.permissao;

import br.jus.cnj.inova.processo.ProcessoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ValorDaCausaJuizadoValidatorTest {

    @Autowired
    private ValorDaCausaJuizadoValidator validator;

    @Autowired
    private ProcessoService processoService;

    @Test
    void validateValorDentroDoLimite() {
        final var processo = this.processoService.findById("5f8ce18b922dd95e3a49a72a").block();
        final var result = this.validator.validate(processo);
        assertTrue(result.isSuccess());
    }

    @Test
    void validateValorNaoExiste() {
        final var processo = this.processoService.findById("5f8ce18b922dd95e3a49abda").block();
        final var result = this.validator.validate(processo);
        assertTrue(result.isSuccess());
    }

    @Test
    void validateValorExcede() {
        final var processo = this.processoService.findById("5f8ce18b922dd95e3a49a8af").block();
        final var result = this.validator.validate(processo);
        assertFalse(result.isSuccess());
    }
}

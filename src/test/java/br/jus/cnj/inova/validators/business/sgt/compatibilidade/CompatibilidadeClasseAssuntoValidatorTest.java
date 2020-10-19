package br.jus.cnj.inova.validators.business.sgt.compatibilidade;

import br.jus.cnj.inova.processo.ProcessoService;
import br.jus.cnj.inova.validators.ValidationResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
class CompatibilidadeClasseAssuntoValidatorTest {

    @Autowired
    private CompatibilidadeClasseAssuntoValidator validator;

    @Autowired
    private ProcessoService processoService;

    @Test
    void validate() {

        final var processoMono = this.processoService.findById("5f820f110020f12c21dbeecb")
                .map(processo -> this.validator.validate(processo))
                .map(ValidationResult::isSuccess);

        StepVerifier.create(processoMono.log())
                .expectNext(true)
                .verifyComplete();

    }
}

package br.jus.cnj.inova.processo.resultado;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.processo.ProcessoService;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class ResultadoServiceTest {

    @Autowired
    ProcessoService processoService;

    @Autowired
    ResultadoService resultadoService;

    @Test
    void save() {
        Mono<Processo> pMono = processoService.findAll()
                .limitRequest(1)
                .single();

        Mono<Resultado> saveResultadoMono = resultadoService.save(pMono, new ValidationResult(Severity.ERROR, "Test Message"));
        StepVerifier.create(saveResultadoMono)
                .expectNextCount(1)
                .expectComplete()
                .verify();

    }
}
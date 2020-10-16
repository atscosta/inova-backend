package br.jus.cnj.inova.processo.resultado;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/processos/{idProcesso}/resultados")
@RequiredArgsConstructor
public class ResultadoController {

    private final ResultadoService resultadoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Resultado> findResultado(@PathVariable String idProcesso) {
        return resultadoService.findById(idProcesso)
                .log();
    }

}


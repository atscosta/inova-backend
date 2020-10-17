package br.jus.cnj.inova.resultado;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;


@RestController
@RequestMapping("/resultados")
@RequiredArgsConstructor
public class ResultadoController {

    private final ResultadoService resultadoService;

    @PostMapping( produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Resultado> save(@RequestBody FiltroResultadoTO filtroResultadoTO) {
        return this.resultadoService.processar(filtroResultadoTO);
    }

}


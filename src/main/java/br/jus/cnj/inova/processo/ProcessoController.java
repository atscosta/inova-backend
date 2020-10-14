package br.jus.cnj.inova.processo;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
public class ProcessoController {

    private final ProcessoService processoService;

    //TODO: Alterar o controller
    @GetMapping(value = "/unidades-judiciarias/{unidadeJudiciaria}/processos", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Processo> findAll(@PathVariable Integer unidadeJudiciaria) {
        return processoService.findAllByUnidadeJudiciaria(unidadeJudiciaria)
                .limitRequest(5);
    }

    @GetMapping(value = "/processos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Processo> findById(@PathVariable String id) {
        return processoService.findById(id)
                .log();
    }


}


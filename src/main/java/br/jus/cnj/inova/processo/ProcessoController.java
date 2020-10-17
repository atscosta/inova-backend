package br.jus.cnj.inova.processo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
public class ProcessoController {

    private final ProcessoService processoService;

    @GetMapping(value = "/processos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Processo> findById(@PathVariable String id) {
        return processoService.findById(id);
    }


}


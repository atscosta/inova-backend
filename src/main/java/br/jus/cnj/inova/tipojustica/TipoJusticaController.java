package br.jus.cnj.inova.tipojustica;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tipos-justica", produces = MediaType.APPLICATION_JSON_VALUE)
public class TipoJusticaController {

    private final TipoJusticaService service;

    @GetMapping("{codigo}")
    public Mono<TipoJustica> findByCodigo(@PathVariable String codigo) {
        return this.service.findByCodigo(codigo);
    }

    @GetMapping
    public Flux<TipoJustica> findAll() {
        return this.service.findAll();
    }
}

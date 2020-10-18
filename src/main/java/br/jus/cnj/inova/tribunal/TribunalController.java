package br.jus.cnj.inova.tribunal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "tribunais", produces = MediaType.APPLICATION_JSON_VALUE)
public class TribunalController {

    private final TribunalService service;

    @GetMapping("{codigo}")
    public Mono<Tribunal> findByCodigo(@PathVariable String codigo) {
        return this.service.findByCodigo(codigo);
    }

    @GetMapping
    public Flux<Tribunal> findAll() {
        return this.service.findAll();
    }

    @GetMapping(params = "codigoJustica")
    public Flux<Tribunal> findAll(@RequestParam String codigoJustica) {
        return this.service.findByCodigoJustica(codigoJustica);
    }

    @GetMapping(params = "uf")
    public Flux<Tribunal> findByUf(@RequestParam String uf) {
        return this.service.findByUf(uf);
    }
}

package br.jus.cnj.inova.tribunal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "tribunais", produces = MediaType.APPLICATION_JSON_VALUE)
public class TribunalController {

    private final TribunalService service;

    @GetMapping
    Flux<Tribunal> findAll() {
        return this.service.findAll();
    }

    @GetMapping(params = "codigoJustica")
    Flux<Tribunal> findAll(@RequestParam String codigoJustica) {
        return this.service.findByCodigoJustica(codigoJustica);
    }

    @GetMapping(params = "uf")
    Flux<Tribunal> findByUf(@RequestParam String uf) {
        return this.service.findByUf(uf);
    }
}

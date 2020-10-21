package br.jus.cnj.inova.tipojustica;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TipoJusticaService {

    private final TipoJusticaRepository repository;

    Mono<TipoJustica> findByCodigo(String codigo) {
        return this.repository.findByCodigo(codigo);
    }

    Flux<TipoJustica> findAll() {
        return this.repository.findAll();
    }
}

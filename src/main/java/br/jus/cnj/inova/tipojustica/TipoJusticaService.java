package br.jus.cnj.inova.tipojustica;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class TipoJusticaService {

    private final TipoJusticaRepository repository;

    Flux<TipoJustica> findAll() {
        return this.repository.findAll();
    }
}

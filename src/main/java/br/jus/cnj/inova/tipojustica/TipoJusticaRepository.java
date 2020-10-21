package br.jus.cnj.inova.tipojustica;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface TipoJusticaRepository extends ReactiveMongoRepository<TipoJustica, String> {

    Mono<TipoJustica> findByCodigo(String codigo);
}

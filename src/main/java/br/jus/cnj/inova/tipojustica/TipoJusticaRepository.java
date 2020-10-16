package br.jus.cnj.inova.tipojustica;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TipoJusticaRepository extends ReactiveMongoRepository<TipoJustica, String> {
}

package br.jus.cnj.inova.unidadejudiciaria;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface UnidadeJudiciariaRepository extends ReactiveMongoRepository<UnidadeJudiciaria, String> {

    Flux<UnidadeJudiciaria> findByCodigo(String codigo);

    Flux<UnidadeJudiciaria> findAllByUf(String uf);
}

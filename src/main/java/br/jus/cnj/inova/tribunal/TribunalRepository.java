package br.jus.cnj.inova.tribunal;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TribunalRepository extends ReactiveMongoRepository<Tribunal, String> {

    Tribunal findFirstByCodigo(String codigo);

    @Query("{'justicas.codigo': ?0}")
    Flux<Tribunal> findByCodigoJustica(String codigoJustica);

    @Query("{ufs: ?0}")
    Flux<Tribunal> findByUf(String uf);

    Mono<Tribunal> findBySigla(Mono<String> sigla);
}

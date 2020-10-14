package br.jus.cnj.inova.detalhamentosgt;

import br.jus.cnj.inova.processo.Grau;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface DetalhamentoSgtRepository extends ReactiveMongoRepository<DetalhamentoSgt, String> {

    @Query("{'justicas.codigo': ?0}")
    Flux<List<DetalhamentoSgt>> findByCodigoJustica(String codigo);

    @Query("{'justicas.codigo': ?0, grau: ?1}")
    Flux<List<DetalhamentoSgt>> findByCodigoJusticaAndGrau(String codigoJustica, Grau grau);
}

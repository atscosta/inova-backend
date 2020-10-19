package br.jus.cnj.inova.resultado;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ResultadoRepository extends ReactiveMongoRepository<Resultado, String> {

    @Query("{'processo.$id': ObjectId('?0')}")
    Flux<Resultado> findByIdProcesso(String idProcesso);
    Mono<Long> countByCodOrgaoJulgador(Long codOrgaoJulgador);
}
    
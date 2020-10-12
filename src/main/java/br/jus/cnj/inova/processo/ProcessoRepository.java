package br.jus.cnj.inova.processo;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProcessoRepository extends ReactiveMongoRepository<Processo, String> {


    @Query("{siglaTribunal : ?0, grau: ?1, 'dadosBasicos.numero': ?2}")
    Flux<Processo> findBySiglaAndGrauAndNumero(String sigal, String grau, String numero);
}
    
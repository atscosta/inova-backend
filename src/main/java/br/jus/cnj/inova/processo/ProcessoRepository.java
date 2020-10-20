package br.jus.cnj.inova.processo;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProcessoRepository extends ReactiveMongoRepository<Processo, String> {

    Mono<Long> countByDadosBasicosOrgaoJulgadorCodigoOrgao(Long codigoOrgao);

    @Query("{siglaTribunal : ?0, grau: ?1, 'dadosBasicos.numero': ?2}")
    Flux<Processo> findBySiglaAndGrauAndNumero(String sigal, String grau, String numero);

    @Query(value = "{'dadosBasicos.orgaoJulgador.codigoOrgao': ?0}", sort = "{'id': 1}")
    Flux<Processo> findAllByUnidadeJudiciaria(Mono<Long> codigoUnidadeJudiciaria);

    @Query(value = "{'dadosBasicos.orgaoJulgador.codigoOrgao': ?0}", sort = "{'id': 1}")
    Flux<Processo> findAllByUnidadeJudiciariaStr(Mono<String> codigoUnidadeJudiciaria);

    Flux<Processo> findAllBySiglaTribunal(Mono<String> sigla);

}

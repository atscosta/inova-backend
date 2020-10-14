package br.jus.cnj.inova.resultado;


import br.jus.cnj.inova.processo.Processo;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ResultadoRepository extends ReactiveMongoRepository<Resultado, String> {


}
    
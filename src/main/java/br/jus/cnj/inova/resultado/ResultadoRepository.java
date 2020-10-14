package br.jus.cnj.inova.resultado;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ResultadoRepository extends ReactiveMongoRepository<Resultado, String> {


}
    
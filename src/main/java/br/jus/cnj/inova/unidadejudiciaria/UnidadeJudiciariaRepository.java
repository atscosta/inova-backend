package br.jus.cnj.inova.unidadejudiciaria;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UnidadeJudiciariaRepository extends MongoRepository<UnidadeJudiciaria, String> {

    UnidadeJudiciaria findByCodigo(String codigo);

    List<UnidadeJudiciaria> findAllByUf();
}

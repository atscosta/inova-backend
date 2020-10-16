package br.jus.cnj.inova.tribunal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TribunalRepository extends MongoRepository<Tribunal, String> {

    @Query("{'justicas.codigo': ?0}")
    List<Tribunal> findByCodigoJustica(String codigoJustica);

    @Query("{ufs: ?0}")
    List<Tribunal> findByUf(String uf);
}

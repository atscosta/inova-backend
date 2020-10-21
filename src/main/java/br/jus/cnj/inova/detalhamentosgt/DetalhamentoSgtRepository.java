package br.jus.cnj.inova.detalhamentosgt;

import br.jus.cnj.inova.processo.Grau;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalhamentoSgtRepository extends MongoRepository<DetalhamentoSgt, String> {

    @Query("{'justicas.codigo': ?0}")
    List<DetalhamentoSgt> findByCodigoJustica(String codigo);

    @Query("{'justicas.codigo': ?0, grau: ?1}")
    List<DetalhamentoSgt> findByCodigoJusticaAndGrau(String codigoJustica, Grau grau);
}

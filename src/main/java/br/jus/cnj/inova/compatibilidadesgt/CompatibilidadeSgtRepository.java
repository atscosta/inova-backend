package br.jus.cnj.inova.compatibilidadesgt;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompatibilidadeSgtRepository extends MongoRepository<CompatibilidadeSgt, String> {

    Optional<CompatibilidadeSgt> findByClasseCodigoAndAssuntoCodigo(Long codigoClasse, Long codigoAssunto);
}

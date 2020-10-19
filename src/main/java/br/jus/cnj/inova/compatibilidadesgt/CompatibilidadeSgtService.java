package br.jus.cnj.inova.compatibilidadesgt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompatibilidadeSgtService {

    private final CompatibilidadeSgtRepository repository;

    public boolean check(Long codigoClasse, Long codigoAssunto) {
        return this.repository.findByClasseCodigoAndAssuntoCodigo(codigoClasse, codigoAssunto)
                .map(CompatibilidadeSgt::getCompativeis)
                .orElse(true);
    }
}

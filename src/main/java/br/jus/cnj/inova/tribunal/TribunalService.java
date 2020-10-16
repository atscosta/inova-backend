package br.jus.cnj.inova.tribunal;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TribunalService {

    private final TribunalRepository repository;

    List<Tribunal> findAll() {
        return this.repository.findAll();
    }

    List<Tribunal> findByCodigoJustica(String codigoJustica) {
        return this.repository.findByCodigoJustica(codigoJustica);
    }

    List<Tribunal> findByUf(String uf) {
        return this.repository.findByUf(uf);
    }
}

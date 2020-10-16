package br.jus.cnj.inova.unidadejudiciaria;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnidadeJudiciariaService {

    private final UnidadeJudiciariaRepository repository;

    public UnidadeJudiciaria findByCodigo(Long codigo) {
        return this.repository.findByCodigo(String.valueOf(codigo));
    }
}

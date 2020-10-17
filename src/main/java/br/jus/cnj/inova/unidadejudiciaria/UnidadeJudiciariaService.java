package br.jus.cnj.inova.unidadejudiciaria;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadeJudiciariaService {

    private final UnidadeJudiciariaRepository repository;

    public UnidadeJudiciaria findByCodigo(String codigo) {
        return this.repository.findByCodigo(codigo);
    }

    public List<UnidadeJudiciaria> findByCodigoTribunal(String codigoTribunal) {
        return this.repository.findAllByTribunalCodigoOrderByUnidadeJudiciaria(codigoTribunal);
    }
}

package br.jus.cnj.inova.processo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
@RequiredArgsConstructor
public class ProcessoService {

    private final ProcessoRepository repository;

    public Flux<Processo> findAll() {
        return repository.findAll();
    }

    public Flux<Processo> findByTribunalAndGrauAndNumero(String sigla, String grau, String numero) {
        return repository.findBySiglaAndGrauAndNumero(sigla, grau, numero);
    }
}

package br.jus.cnj.inova.processo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ProcessoService {

    private final ProcessoRepository repository;

    public Flux<Processo> findAll() {
        return repository.findAll();
    }

    public Flux<Processo> findAllBySigla(Mono<String> sigla) {
        return repository.findAllBySiglaTribunal(sigla);
    }

    public Flux<Processo> findAllByUnidadeJudiciaria(Mono<Long> codUnidadeJudiciaria) {
        return repository.findAllByUnidadeJudiciaria(codUnidadeJudiciaria);
    }

    public Mono<Processo> findById(String id) {
        return repository.findById(id);
    }
}

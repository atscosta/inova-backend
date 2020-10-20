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
        final var codLong = repository.findAllByUnidadeJudiciaria(codUnidadeJudiciaria);
        final var codString = repository.findAllByUnidadeJudiciariaStr(codUnidadeJudiciaria.map(String::valueOf));
        return Flux.concat(codLong, codString);
    }

    public Mono<Long> countAllByUnidadeJudiciaria(Mono<Long> codUnidadeJudiciaria) {
        final var codLong = repository.countAllByUnidadeJudiciaria(codUnidadeJudiciaria);
        final var codString = repository.countAllByUnidadeJudiciariaStr(codUnidadeJudiciaria.map(String::valueOf));
        return Flux.concat(codLong, codString).reduce(Long::sum);
    }

    public Mono<Processo> findById(String id) {
        return repository.findById(id);
    }
}

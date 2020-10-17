package br.jus.cnj.inova.processo.resultado;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.processo.ProcessoService;
import br.jus.cnj.inova.processo.capa.DadosBasicos;
import br.jus.cnj.inova.processo.capa.OrgaoJulgador;
import br.jus.cnj.inova.validators.Validation;
import br.jus.cnj.inova.validators.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class ResultadoService {

    private final ResultadoRepository repository;

    public Mono<Resultado> save(Processo processo, Set<Validation> validations) {
        return this.save(Mono.just(processo), validations);
    }

    public Mono<Resultado> save(Mono<Processo> processoMono, Set<Validation> validations) {
        return processoMono.map(p -> new Resultado(p, validations))
                .flatMap(repository::save);
    }

    public Mono<Resultado> findById(String idProcesso) {
        return this.repository.findById(idProcesso);
    }


}

package br.jus.cnj.inova.resultado;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.processo.ProcessoService;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Validation;
import br.jus.cnj.inova.validators.ValidatorService;
import br.jus.cnj.inova.validators.ValidatorType;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ResultadoService {

    private final ResultadoRepository repository;
    private final ProcessoService processoService;
    private final ValidatorService validatorService;
    
    @Value("${windowSize}")
    private int windowSize;
    
    public Flux<Resultado> validateByCodigoUnidadeJudiciaria(Mono<Long> codUnidadeJudiciaria,
        @Nullable ValidatorType validatorType) {

        final var validators = Optional.ofNullable(validatorType)
                .map(this.validatorService::getAllValidatorsByType)
                .orElse(this.validatorService.getAllByEnabledValidators());

        return this.processoService
                .findAllByUnidadeJudiciaria(codUnidadeJudiciaria)
                .flatMap(processo -> this.validateByProcesso(processo, validators));
    }

    //XXX Paralelizar validacoes
    public Mono<Resultado> validateByProcesso(Processo processo, List<ProcessoValidator> processoValidators) {
        final var validationResulList = Flux.fromIterable(processoValidators)
            .window(this.windowSize)
            .flatMap(validatorFlux ->
                validatorFlux.map(validateProcessoWithValidators(processo)))
            .subscribeOn(Schedulers.parallel())
            .collectList()
            .block();
    
        return this.save(processo, validationResulList);
    }
    
    @NotNull
    private Function<ProcessoValidator, Validation> validateProcessoWithValidators(Processo processo) {
        return v -> new Validation(v, v.validate(processo));
    }
    
    public Mono<Resultado> save(Processo processo, List<Validation> validations) {
        return this.save(Mono.just(processo), validations);
    }

    public Mono<Resultado> save(Mono<Processo> processoMono, List<Validation> validations) {
        return processoMono.map(p -> new Resultado(p, validations))
                .flatMap(repository::save);
    }

    public Mono<Resultado> findById(String idProcesso) {
        return this.repository.findById(idProcesso);
    }

    public Flux<Resultado> processar(FiltroResultadoTO filtro) {
        return this.validateByCodigoUnidadeJudiciaria(Mono.just(filtro.getCodUnidadeJudiciaria()), ValidatorType.MOVIMENTOS);
    }
}

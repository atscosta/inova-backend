package br.jus.cnj.inova.resultado;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.processo.ProcessoService;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Validation;
import br.jus.cnj.inova.validators.ValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ResultadoService {

    private static final String VALIDATOR_ERROR = "Erro na execução do validador: %s.";

    private final ResultadoRepository repository;
    private final ProcessoService processoService;
    private final ValidatorService validatorService;

    @Value("${windowSize}")
    private int windowSize;

    public Flux<Resultado> validateByCodigoUnidadeJudiciaria(Mono<Long> codUnidadeJudiciaria) {
        return this.validateByCodigoUnidadeJudiciaria(codUnidadeJudiciaria,
                this.validatorService.getAllByEnabledValidators());
    }

    public Flux<Resultado> validateByCodigoUnidadeJudiciaria(Mono<Long> codUnidadeJudiciaria,
                                                             List<ProcessoValidator> processoValidators) {
        return this.processoService
                .findAllByUnidadeJudiciaria(codUnidadeJudiciaria)
                .flatMap(processo -> this.validateByProcesso(processo, processoValidators));
    }

    public Mono<Resultado> validateByProcesso(Processo processo) {
        return this.validateByProcesso(processo, this.validatorService.getAllByEnabledValidators());
    }

    public Mono<Resultado> validateByProcesso(Processo processo, List<ProcessoValidator> processoValidators) {
        return Flux.fromIterable(processoValidators)
                .window(this.windowSize)
                .flatMap(validatorFlux -> validatorFlux
                        .map(validator -> new Validation(validator, validator.validate(processo)))
                        .onErrorContinue((throwable, obj) -> log.error(String.format(VALIDATOR_ERROR, throwable.getMessage()), throwable))
                )
                .subscribeOn(Schedulers.parallel())
                .collectList()
                .flatMap(resultList -> this.save(Mono.just(processo), resultList));
    }

    public Mono<Resultado> save(Mono<Processo> processoMono, List<Validation> validations) {
        return processoMono.map(p -> new Resultado(p, validations)).flatMap(repository::save);
    }

    public Mono<Resultado> findById(String idProcesso) {
        return this.repository.findById(idProcesso);
    }

    public Flux<Resultado> processar(FiltroResultadoTO filtro) {
        return Optional.ofNullable(filtro.getIdProcesso())
                .map(this.processoService::findById)
                .map(processoMono -> processoMono.flatMapMany(this::validateByProcesso))
                .orElse(this.validateByCodigoUnidadeJudiciaria(Mono.just(filtro.getCodUnidadeJudiciaria())));
    }

    public Mono<Long> countByCodigoUnidadeJudiciaria(Long codOrgaoJulgador) {
        return this.repository.countByCodOrgaoJulgador(codOrgaoJulgador);
    }

    public Mono<Long> countValidadosComErro(Long codigo) {
        return this.repository.countPossuemFalhasPorOrgaoJulgador(codigo);
    }

    public Mono<Long> countValidadosComSucesso(Long codigo) {
        Mono<Long> totalValidadosMono = this.countByCodigoUnidadeJudiciaria(codigo);
        Mono<Long> validadosComErroMono = this.countValidadosComErro(codigo);
        return Mono.zip(totalValidadosMono, validadosComErroMono, (total, erros) -> total - erros);
    }
}

package br.jus.cnj.inova.resultado;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.processo.ProcessoService;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Validation;
import br.jus.cnj.inova.validators.ValidatorType;
import br.jus.cnj.inova.validators.ValidatorsManager;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ResultadoService {

    private final ResultadoRepository repository;
    private final ProcessoService processoService;
    private final ValidatorsManager validatorsManager;

    public Flux<Resultado> validateByCodigoUnidadeJudiciaria(Mono<Long> codUnidadeJudiciaria) {
        return validateByCodigoUnidadeJudiciaria(codUnidadeJudiciaria, null);
    }

    public Flux<Resultado> validateByCodigoUnidadeJudiciaria(Mono<Long> codUnidadeJudiciaria,
        @Nullable ValidatorType validatorType) {

        List<ProcessoValidator> pValidators = Optional.ofNullable(validatorType)
                .map(this.validatorsManager::getValidatorsByType)
                .orElse(this.validatorsManager.getAllValidators());

        return this.processoService
                .findAllByUnidadeJudiciaria(codUnidadeJudiciaria)
                .flatMap(processo -> this.validateByProcesso(processo, pValidators));
    }

    public Mono<Resultado> validateByProcesso(Processo processo, List<ProcessoValidator> processoValidators) {
        Set<Validation> validations = processoValidators.stream()
                .map(validator -> new Validation(validator, validator.validate(processo)))
                .collect(Collectors.toSet());

        return this.save(processo, validations);
    }

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

    public Flux<Resultado> processar(FiltroResultadoTO filtro) {
        return this.validateByCodigoUnidadeJudiciaria(Mono.just(filtro.getCodUnidadeJudiciaria()), ValidatorType.MOVIMENTOS);
    }
}

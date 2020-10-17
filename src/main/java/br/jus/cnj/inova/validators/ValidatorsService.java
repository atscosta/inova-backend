package br.jus.cnj.inova.validators;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.processo.ProcessoService;
import br.jus.cnj.inova.processo.resultado.Resultado;
import br.jus.cnj.inova.processo.resultado.ResultadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ValidatorsService {

    private final ValidatorsManager validatorsManager;
    private final ProcessoService processoService;
    private final ResultadoService resultadoService;

    public Flux<Resultado> validateByCodigoUnidadeJudiciaria(Mono<Long> codUnidadeJudiciaria, Optional<ValidatorType> validatorType) {

        List<ProcessoValidator> pValidators = validatorType
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

        return resultadoService.save(processo, validations);
    }


}

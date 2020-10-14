package br.jus.cnj.inova.validators;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.resultados.ResultadosService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ValidatorsService {

    private final ValidatorsManager validatorsManager;
    private final ResultadosService resultadosService;

    void validate(Processo processo) {
        Flux.fromIterable(this.validatorsManager.getAllValidators())
                .map(validator -> validator.validate(processo))
                .subscribe(result -> this.resultadosService.save(processo, result));
    }
}
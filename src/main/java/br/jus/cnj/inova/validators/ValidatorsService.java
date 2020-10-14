package br.jus.cnj.inova.validators;

import br.jus.cnj.inova.processo.Processo;
import java.util.List;

import br.jus.cnj.inova.resultado.ResultadoService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ValidatorsService {

    private final ValidatorsManager validatorsManager;
    private final ResultadoService resultadoService;

    void validate(Processo processo) {
        Flux.fromIterable(this.validatorsManager.getAllValidators())
                .map(validator -> validator.validate(processo))
                .subscribe(result -> this.resultadoService.save(processo, result));
    }
    
    public List<ProcessoValidator> getAllValidators(){
        return this.validatorsManager.getAllValidators();
    }
}

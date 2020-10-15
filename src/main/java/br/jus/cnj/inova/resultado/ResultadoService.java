package br.jus.cnj.inova.resultado;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.validators.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ResultadoService {

    private final ResultadoRepository repository;

    public Mono<Resultado> save(Processo processo, ValidationResult validationResult) {
        return this.save(Mono.just(processo), validationResult);
    }

    public Mono<Resultado> save(Mono<Processo> processo, ValidationResult validationResult) {
        return processo.map(p -> createResultado(validationResult, p))
                .flatMap(repository::save);
    }

    @NotNull
    private Resultado createResultado(ValidationResult validationResult, Processo processo) {
        Resultado resultado = new Resultado();
        resultado.setProcesso(processo);
        resultado.addValidation(validationResult);
        return resultado;
    }
}

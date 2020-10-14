package br.jus.cnj.inova.resultado;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.validators.business.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ResultadoService {

    private final ResultadoRepository repository;

    public Mono<Resultado> save(Processo processo, ValidationResult validationResult) {

        Resultado resultado = new Resultado();
        resultado.setProcesso(processo);
        resultado.addValidation(validationResult);
        return repository.save(resultado);
    }
}

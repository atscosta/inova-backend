package br.jus.cnj.inova.processo.resultado;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/processos/{idProcesso}/resultados")
@RequiredArgsConstructor
public class ResultadoController {

    private final ResultadoService resultadoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Resultado> findResultadoByIdProcesso(@PathVariable String idProcesso) {
        return resultadoService.findById(idProcesso);
    }

}


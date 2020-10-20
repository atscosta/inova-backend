package br.jus.cnj.inova.processo;

import br.jus.cnj.inova.resultado.Resultado;
import br.jus.cnj.inova.resultado.ResultadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/processos")
@RequiredArgsConstructor
public class ProcessoController {

    private final ProcessoService processoService;
    private final ResultadoService resultadoService;
    private final ProcessoTOConverter processoConverter;

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ProcessoTO> findById(@PathVariable String id) {
        return processoService.findById(id).flatMap(this.processoConverter::convert);
    }

    @GetMapping(value = "{idProcesso}/resultados", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Resultado> findResultadoByIdProcesso(@PathVariable String idProcesso) {
        return resultadoService.findById(idProcesso);
    }
}


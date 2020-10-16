package br.jus.cnj.inova.resultado;

import br.jus.cnj.inova.processo.ProcessoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/resultados")
@RequiredArgsConstructor
public class ResultadoController {

    private final ProcessoService processoService;
    private final ResultadoService resultadoService;

}


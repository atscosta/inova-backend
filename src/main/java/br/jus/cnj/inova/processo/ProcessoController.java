package br.jus.cnj.inova.processo;

import br.jus.cnj.inova.processo.capa.DadosBasicos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/processos")
@RequiredArgsConstructor
public class ProcessoController {

    private final ProcessoService processoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<DadosBasicos> findByNumero(@RequestParam String sigla, @RequestParam String grau, @RequestParam String numero) {
        return processoService.findByTribunalAndGrauAndNumero(sigla, grau, numero)
                .map(Processo::getDadosBasicos);
    }
}


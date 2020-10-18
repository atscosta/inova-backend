package br.jus.cnj.inova.unidadejudiciaria;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.processo.ProcessoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/unidades-judiciarias", produces = MediaType.APPLICATION_JSON_VALUE)
public class UnidadeJudiciariaController {

    private final UnidadeJudiciariaService service;
    private final ProcessoService processoService;

    @GetMapping("{codigo}")
    public UnidadeJudiciaria findByCodigo(@PathVariable String codigo) {
        return this.service.findByCodigo(codigo);
    }

    @GetMapping(params = "codigoTribunal")
    public List<UnidadeJudiciaria> findByCodigoTribunal(@RequestParam String codigoTribunal) {
        return this.service.findByCodigoTribunal(codigoTribunal);
    }


    @GetMapping(value = "{unidadeJudiciaria}/processos", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Processo> findResultadoByIdProcesso(@PathVariable Long unidadeJudiciaria,
                                                    @RequestParam(required = false) Integer skip,
                                                    @RequestParam(required = false) Integer size) {

        return processoService.findAllByUnidadeJudiciaria(Mono.just(unidadeJudiciaria))
                .skip(Optional.ofNullable(skip).orElse(0))
                .take(Optional.ofNullable(size).orElse(30));
    }

}

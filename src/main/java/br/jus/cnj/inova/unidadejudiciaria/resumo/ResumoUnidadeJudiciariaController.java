package br.jus.cnj.inova.unidadejudiciaria.resumo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("unidades-judiciarias/{codigo}/resumo")
public class ResumoUnidadeJudiciariaController {

    private final ResumoUnidadeJudiciariaService service;

    @GetMapping
    public Mono<ResumoUnidadeJudiciaria> findByCodigoUnidadeJudiciaria(@PathVariable Long codigo) {
        return this.service.findByCodigoUnidadeJudiciaria(codigo);
    }
}

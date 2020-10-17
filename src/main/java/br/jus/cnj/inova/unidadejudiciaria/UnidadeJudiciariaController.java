package br.jus.cnj.inova.unidadejudiciaria;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/unidades-judiciarias", produces = MediaType.APPLICATION_JSON_VALUE)
public class UnidadeJudiciariaController {

    private final UnidadeJudiciariaService service;

    @GetMapping("{codigo}")
    public UnidadeJudiciaria findByCodigo(@PathVariable String codigo) {
        return this.service.findByCodigo(codigo);
    }

    @GetMapping(params = "codigoTribunal")
    public List<UnidadeJudiciaria> findByCodigoTribunal(@RequestParam String codigoTribunal) {
        return this.service.findByCodigoTribunal(codigoTribunal);
    }
}

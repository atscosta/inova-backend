package br.jus.cnj.inova.detalhamentosgt;

import br.jus.cnj.inova.processo.Grau;
import br.jus.cnj.inova.unidadejudiciaria.Justica;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalhamentoSgtService {

    private final DetalhamentoSgtRepository repository;

    public Flux<List<DetalhamentoSgt>> findByJustica(Justica justica) {
        return this.repository.findByCodigoJustica(justica.getCodigo());
    }

    public Flux<List<DetalhamentoSgt>> findByJusticaAndGrau(Justica justica, Grau grau) {
        return this.repository.findByCodigoJusticaAndGrau(justica.getCodigo(), grau);
    }
}

package br.jus.cnj.inova.unidadejudiciaria;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class UnidadeJudiciariaService {

    private final UnidadeJudiciariaRepository repository;

    public Flux<UnidadeJudiciaria> findByCodigo(String codigo) {
        return this.repository.findByCodigo(codigo)
                .cache(Duration.ofMinutes(10));
    }
}

package br.jus.cnj.inova.unidadejudiciaria.resumo;

import br.jus.cnj.inova.processo.ProcessoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ResumoUnidadeJudiciariaService {

    private final ProcessoService processoService;

    public Mono<ResumoUnidadeJudiciaria> findByCodigoUnidadeJudiciaria(String codigo) {
        return this.processoService.countByCodigoUnidadeJudiciaria(codigo)
                .map(countProcessos -> {

                    // TODO: implementar a forma correta de obter esses totais

                    final var countProcessosValidados = Math.round(Math.random() * countProcessos);
                    final var countProcessosValidadosComSucesso = Math.round(Math.random() * countProcessosValidados);
                    return ResumoUnidadeJudiciaria.builder()
                            .countProcessos(countProcessos)
                            .countProcessosValidados(countProcessosValidados)
                            .countProcessosValidadosComSucesso(countProcessosValidadosComSucesso)
                            .build();
                });
    }
}

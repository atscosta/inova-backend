package br.jus.cnj.inova.unidadejudiciaria.resumo;

import br.jus.cnj.inova.processo.ProcessoService;
import br.jus.cnj.inova.resultado.ResultadoService;
import br.jus.cnj.inova.unidadejudiciaria.resumo.ResumoUnidadeJudiciaria.ResumoUnidadeJudiciariaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ResumoUnidadeJudiciariaService {

    private final ProcessoService processoService;
    private final ResultadoService resultadoService;

    public Mono<ResumoUnidadeJudiciaria> findByCodigoUnidadeJudiciaria(Long codUnidadeJudiciaria) {
        Mono<Long> codUnidadeMono = Mono.just(codUnidadeJudiciaria);
        Mono<Long> qtdProcessos = processoService.countByCodigoUnidadeJudiciaria(codUnidadeJudiciaria);
        Mono<Long> qtdProcessosValidados = resultadoService.countByCodigoUnidadeJudiciaria(codUnidadeJudiciaria);
        Mono<Long> qtdProcessosValidadosSucesso = resultadoService.countValidadosComSucesso(codUnidadeJudiciaria);
        Mono<Long> qtdProcessosValidadosErro = resultadoService.countValidadosComErro(codUnidadeJudiciaria);

        return Mono.just(ResumoUnidadeJudiciaria.builder())
                .zipWith(codUnidadeMono, ResumoUnidadeJudiciariaBuilder::codigoUnidadeJudiciaria)
                .zipWith(qtdProcessos, ResumoUnidadeJudiciariaBuilder::countProcessos)
                .zipWith(qtdProcessosValidados, ResumoUnidadeJudiciariaBuilder::countProcessosValidados)
                .zipWith(qtdProcessosValidadosSucesso, ResumoUnidadeJudiciariaBuilder::countProcessosValidadosComSucesso)
                .zipWith(qtdProcessosValidadosErro, ResumoUnidadeJudiciariaBuilder::countProcessosValidadosComErro)
                .map(ResumoUnidadeJudiciariaBuilder::build);
    }
}

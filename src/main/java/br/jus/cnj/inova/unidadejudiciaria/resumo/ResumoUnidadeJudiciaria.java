package br.jus.cnj.inova.unidadejudiciaria.resumo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResumoUnidadeJudiciaria {
    private final Long codigoUnidadeJudiciaria;
    private final Long countProcessos;
    private final Long countProcessosValidados;
    private final Long countProcessosValidadosComSucesso;
    private final Long countProcessosValidadosComErro;
}

package br.jus.cnj.inova.unidadejudiciaria.resumo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResumoUnidadeJudiciaria {
    private String codigoUnidadeJudiciaria;
    private Long countProcessos;
    private Long countProcessosValidados;
    private Long countProcessosValidadosComSucesso;
}

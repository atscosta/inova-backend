package br.jus.cnj.inova.processo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ProcessoTO {
    private final String id;
    private final String npu;
    private final Long codigoClasse;
    private final String descricaoClasse;
    private final LocalDate dataDistribuicao;
    private final int validacoesRealizadas;
    private final int erros;
}

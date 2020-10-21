package br.jus.cnj.inova.resultado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiltroResultadoTO {

    private long codUnidadeJudiciaria;
    private String idProcesso;
}

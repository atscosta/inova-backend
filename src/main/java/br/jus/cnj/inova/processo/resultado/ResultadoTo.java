package br.jus.cnj.inova.processo.resultado;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResultadoTo {

    private String idProcesso;
    private List<String> validacoes;

}

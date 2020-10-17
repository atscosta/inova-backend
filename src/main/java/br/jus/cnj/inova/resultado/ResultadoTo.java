package br.jus.cnj.inova.resultado;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResultadoTo {

    private String idProcesso;
    private List<String> validacoes;

}

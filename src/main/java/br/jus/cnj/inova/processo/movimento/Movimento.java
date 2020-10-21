
package br.jus.cnj.inova.processo.movimento;

import br.jus.cnj.inova.processo.capa.OrgaoJulgador;
import br.jus.cnj.inova.processo.movimento.complemento.ComplementoNacional;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movimento {

    private List<ComplementoNacional> complementoNacional;
    private String dataHora;// 20180221130534
    private String identificadorMovimento;
    private MovimentoNacional movimentoNacional;
    private MovimentoLocal movimentoLocal;
    private OrgaoJulgador orgaoJulgador;

}

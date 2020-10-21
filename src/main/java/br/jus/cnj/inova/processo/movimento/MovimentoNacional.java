
package br.jus.cnj.inova.processo.movimento;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovimentoNacional {

    private Long codigoNacional;
    private List<String> complemento;

}

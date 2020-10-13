
package br.jus.cnj.inova.processo.capa;

import br.jus.cnj.inova.validations.NumeroProcessoConstraint;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DadosBasicos {

    private List<Assunto> assunto;
    private Long classeProcessual;
    private String codigoLocalidade;
    private Long dscSistema;
    private String grau;
    private Long nivelSigilo;
    
    @NumeroProcessoConstraint
    private String numero;
    
    private OrgaoJulgador orgaoJulgador;
    private Long procEl;
    private String siglaTribunal;

}

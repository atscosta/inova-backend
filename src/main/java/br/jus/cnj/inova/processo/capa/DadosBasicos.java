
package br.jus.cnj.inova.processo.capa;

import br.jus.cnj.inova.validators.field.NumeroProcessoConstraint;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DadosBasicos {

    @NumeroProcessoConstraint
    private String numero;
    private Long classeProcessual;
    private Long dscSistema;// ?
    private Long nivelSigilo;
    private BigDecimal valorCausa;
    
    private OrgaoJulgador orgaoJulgador;
    private Long procEl;
    private String siglaTribunal;
    private String dataAjuizamento;// 20100108000000
    
    private List<Assunto> assunto;
}


package br.jus.cnj.inova.processo.capa;

import br.jus.cnj.inova.validators.field.mandatory.CampoObrigatorio;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DadosBasicos implements CampoObrigatorio {
    
    private String numero;
    private Long classeProcessual;
    private Long dscSistema;
    private Long nivelSigilo;
    private BigDecimal valorCausa;

    private OrgaoJulgador orgaoJulgador;
    private Long procEl;
    private String siglaTribunal;
    private String dataAjuizamento;

    private List<Assunto> assunto;
    
    @Override
    public boolean isVazio() {
        return (numero == null || numero.isBlank())
            && (classeProcessual == null || classeProcessual == 0)
            && (dscSistema == null || dscSistema == 0)
            && (nivelSigilo == null || nivelSigilo == 0)
            && (valorCausa == null || BigDecimal.ZERO.equals(valorCausa))
            && (orgaoJulgador == null || orgaoJulgador.isVazio())
            && (procEl == null || procEl == 0)
            && (valorCausa == null || BigDecimal.ZERO.equals(valorCausa))
            && (siglaTribunal == null || siglaTribunal.isBlank())
            && (dataAjuizamento == null || dataAjuizamento.isBlank());
            
    }
}

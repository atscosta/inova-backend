
package br.jus.cnj.inova.processo.capa;

import br.jus.cnj.inova.validators.field.mandatory.CampoObrigatorio;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrgaoJulgador implements CampoObrigatorio {

    private Long codigoMunicipioIBGE;
    private Long codigoOrgao;
    private String instancia;
    private String nomeOrgao;
    
    @Override
    public boolean isVazio() {
        return (codigoMunicipioIBGE == null || codigoMunicipioIBGE == 0)
            && (codigoOrgao == null || codigoOrgao == 0)
            && (instancia == null || instancia.isBlank())
            && (nomeOrgao == null || nomeOrgao.isBlank());
    }
}


package br.jus.cnj.inova.processo.capa;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrgaoJulgador {

    private Long codigoMunicipioIBGE;
    private Long codigoOrgao;
    private String instancia;
    private String nomeOrgao;

}

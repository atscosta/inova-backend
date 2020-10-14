
package br.jus.cnj.inova.processo;

import br.jus.cnj.inova.processo.capa.DadosBasicos;
import br.jus.cnj.inova.processo.movimento.Movimento;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "processos")
public class Processo {

    @Id
    private String id;
    private long millisInsercao;
    private DadosBasicos dadosBasicos;
    private String grau;
    private List<Movimento> movimento;
    private String siglaTribunal;

}

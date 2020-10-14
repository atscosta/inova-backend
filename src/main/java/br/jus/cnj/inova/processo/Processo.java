
package br.jus.cnj.inova.processo;

import br.jus.cnj.inova.processo.capa.DadosBasicos;
import br.jus.cnj.inova.processo.movimento.Movimento;
import java.math.BigInteger;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "processos")
public class Processo {

    @Id
    private BigInteger _id;
    private long millisInsercao;
    private DadosBasicos dadosBasicos;
    private String siglaTribunal;
    private List<Movimento> movimento;
    private Grau grau;

}

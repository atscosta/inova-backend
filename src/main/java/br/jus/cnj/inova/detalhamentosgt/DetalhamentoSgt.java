package br.jus.cnj.inova.detalhamentosgt;

import br.jus.cnj.inova.processo.Grau;
import br.jus.cnj.inova.unidadejudiciaria.Justica;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "detalhamentosgt")
public class DetalhamentoSgt {
    @Id
    private String id;
    private String key;
    private String descricao;
    private Grau grau;
    private List<Justica> justicas;
}

package br.jus.cnj.inova.tribunal;

import br.jus.cnj.inova.unidadejudiciaria.Justica;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "tribunais")
public class Tribunal {
    @Id
    private String id;
    private String codigo;
    @Field("descricao")
    private String sigla;
    private List<Justica> justicas;
    private List<String> ufs;
}

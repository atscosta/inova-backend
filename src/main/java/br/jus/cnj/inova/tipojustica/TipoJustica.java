package br.jus.cnj.inova.tipojustica;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "justicas")
public class TipoJustica {
    @Id
    private String id;
    private String codigo;
    private String descricao;
}

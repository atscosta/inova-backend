package br.jus.cnj.inova.compatibilidadesgt;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "compatibilidadesgt")
public class CompatibilidadeSgt {
    @Id
    private String id;
    private Classe classe;
    private Assunto assunto;
    private Boolean compativeis;
}

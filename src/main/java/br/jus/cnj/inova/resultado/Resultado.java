package br.jus.cnj.inova.resultado;

import br.jus.cnj.inova.processo.Processo;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "resultados")
public class Resultado {

    @Id
    private String id;

    @DBRef(lazy = true)
    private Processo processo;

    private List<String> validacoes;

    public Resultado(Processo processo, List<String> validacoes) {
        this.processo = processo;
        this.validacoes = validacoes;
    }
}

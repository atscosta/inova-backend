package br.jus.cnj.inova.resultado;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.validators.business.ValidationResult;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "resultados")
@Data
public class Resultado {

    @Id
    private String id;

    @DBRef(lazy = true)
    private Processo processo;

    private Set<ValidationResult> validationResults;

    public void addValidation(ValidationResult result) {
        if (validationResults == null) {
            this.validationResults = new HashSet<>();
        }

        this.validationResults.add(result);
    }

}

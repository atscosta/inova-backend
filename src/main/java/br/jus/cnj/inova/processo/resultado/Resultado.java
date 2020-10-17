package br.jus.cnj.inova.processo.resultado;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.validators.ValidationResult;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "resultados")
@NoArgsConstructor
@Data
public class Resultado {

    @Id
    private String id;
    private String numero;
    private String siglaTribunal;
    private Long classeProcessual;
    private Long codOrgaoJulgador;

    private Set<ValidationResult> validationResults;

    public Resultado(Processo processo) {
        this.id = processo.getId();
    }

    public void addValidation(ValidationResult result) {
        if (validationResults == null) {
            this.validationResults = new HashSet<>();
        }

        this.validationResults.add(result);
    }

}

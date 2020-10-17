package br.jus.cnj.inova.processo.resultado;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.processo.capa.DadosBasicos;
import br.jus.cnj.inova.processo.capa.OrgaoJulgador;
import br.jus.cnj.inova.validators.Validation;
import br.jus.cnj.inova.validators.ValidationResult;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Optional;
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

    private Set<Validation> validations;

    public Resultado(Processo processo, Set<Validation> validations) {
        this.id = processo.getId();

        Optional<DadosBasicos> dadosBasicosOptional = Optional.of(processo.getDadosBasicos());

        final String numeroProcesso = dadosBasicosOptional
                .map(DadosBasicos::getNumero)
                .orElse(null);

        final Long codClasse = dadosBasicosOptional
                .map(DadosBasicos::getClasseProcessual)
                .orElse(null);

        final Long codOrgaoJulgador = dadosBasicosOptional
                .map(DadosBasicos::getOrgaoJulgador)
                .map(OrgaoJulgador::getCodigoOrgao)
                .orElse(null);

        this.setClasseProcessual(codClasse);
        this.setNumero(numeroProcesso);
        this.setCodOrgaoJulgador(codOrgaoJulgador);
        this.setSiglaTribunal(processo.getSiglaTribunal());
        this.validations = validations;
    }

}

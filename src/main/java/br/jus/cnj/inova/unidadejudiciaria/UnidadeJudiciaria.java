package br.jus.cnj.inova.unidadejudiciaria;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "unidadesjudiciarias")
public class UnidadeJudiciaria {

    @Id
    private String id;
    private String codigo;
    private String unidadeJudiciaria;
    private Justica justica;
    private Tribunal tribunal;
    private String uf;
    private TipoUnidade tipoUnidade;
    private ClassificacaoUnidade classificacaoUnidade;
    private Integer unidadesJudiciarias;
    private Integer estoque;
    private Integer taxaCongestionamentoLiquida;
    private Integer taxaCongestionamentoTotal;
}

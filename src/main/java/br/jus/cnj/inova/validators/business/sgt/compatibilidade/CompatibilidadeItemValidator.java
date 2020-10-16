package br.jus.cnj.inova.validators.business.sgt.compatibilidade;

import br.jus.cnj.inova.detalhamentosgt.DetalhamentoSgt;
import br.jus.cnj.inova.detalhamentosgt.DetalhamentoSgtService;
import br.jus.cnj.inova.processo.Grau;
import br.jus.cnj.inova.sgt.SgtClientFacade;
import br.jus.cnj.inova.unidadejudiciaria.UnidadeJudiciaria;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompatibilidadeItemValidator {

    private final SgtClientFacade sgtClient;
    private final DetalhamentoSgtService detalhamentoService;

    ValidationResult validate(UnidadeJudiciaria unidadeJudiciaria, Grau grau, TipoItemEnum tipoItem, Long seqItem) {
        final var detalhamentosDoItem = this.sgtClient.getArrayDetalhesItem(seqItem, tipoItem);
        return this.avaliarPorJustica(unidadeJudiciaria, grau, detalhamentosDoItem);
    }

    private ValidationResult avaliarPorJustica(
            UnidadeJudiciaria unidadeJudiciaria, Grau grau, Map<String, String> detalhamentosDoItem) {
        final var detalhamentosDaUnidadeJudiciaria = this.detalhamentoService
                .findByJustica(unidadeJudiciaria.getJustica());
        if (this.findDetalhamentosCompativeis(detalhamentosDaUnidadeJudiciaria, detalhamentosDoItem).isEmpty()) {
            return new ValidationResult(Severity.ERROR,
                    "O item não é compatível com o tipo de justiça da unidade judiciária.");
        } else {
            return this.avaliarPorJusticaEGrau(unidadeJudiciaria, grau, detalhamentosDoItem);
        }
    }

    private ValidationResult avaliarPorJusticaEGrau(
            UnidadeJudiciaria unidadeJudiciaria, Grau grau, Map<String, String> detalhamentosDoItem) {
        final var detalhamentosDaUnidadeJudiciaria = this.detalhamentoService
                .findByJusticaAndGrau(unidadeJudiciaria.getJustica(), grau);
        if (this.findDetalhamentosCompativeis(detalhamentosDaUnidadeJudiciaria, detalhamentosDoItem).isEmpty()) {
            return new ValidationResult(Severity.WARNING, "O item não é compatível com o grau do processo.");
        } else {
            return new ValidationResult();
        }
    }

    private List<DetalhamentoSgt> findDetalhamentosCompativeis(
            List<DetalhamentoSgt> detalhamentosDaUnidadeJudiciaria, Map<String, String> detalhamentosDoItem) {
        return detalhamentosDaUnidadeJudiciaria.stream()
                .filter(detalhamentoSgt -> "S".equals(detalhamentosDoItem.get(detalhamentoSgt.getKey())))
                .collect(Collectors.toList());
    }
}

package br.jus.cnj.inova.validators.compatibilidade;

import br.jus.cnj.inova.detalhamentosgt.DetalhamentoSgt;
import br.jus.cnj.inova.detalhamentosgt.DetalhamentoSgtService;
import br.jus.cnj.inova.processo.Grau;
import br.jus.cnj.inova.sgt.ReactiveSgtClient;
import br.jus.cnj.inova.unidadejudiciaria.UnidadeJudiciaria;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompatibilidadeItemValidator {

    private final ReactiveSgtClient sgtClient;
    private final DetalhamentoSgtService detalhamentoService;

    Flux<ValidationResult> validate(UnidadeJudiciaria unidadeJudiciaria, Grau grau, TipoItemEnum tipoItem, Long seqItem) {
        return this.avaliarPorJustica(unidadeJudiciaria, grau, tipoItem, seqItem);
    }

    @NotNull
    private Flux<ValidationResult> avaliarPorJustica(UnidadeJudiciaria unidadeJudiciaria, Grau grau, TipoItemEnum tipoItem, Long seqItem) {
        return Flux.combineLatest(
                this.detalhamentoService.findByJustica(unidadeJudiciaria.getJustica()),
                this.sgtClient.getArrayDetalhesItem(seqItem, tipoItem),
                this::findDetalhamentosCompativeis)
                .flatMap(compativeis -> compativeis.isEmpty() ?
                        Flux.just(new ValidationResult(Severity.ERROR, "O item não é compatível com o tipo de justiça da unidade judiciária.")) :
                        this.avaliarPorJusticaEGrau(unidadeJudiciaria, grau, tipoItem, seqItem));
    }

    @NotNull
    private Flux<ValidationResult> avaliarPorJusticaEGrau(UnidadeJudiciaria unidadeJudiciaria, Grau grau, TipoItemEnum tipoItem, Long seqItem) {
        return Flux.combineLatest(
                this.detalhamentoService.findByJusticaAndGrau(unidadeJudiciaria.getJustica(), grau),
                this.sgtClient.getArrayDetalhesItem(seqItem, tipoItem),
                this::findDetalhamentosCompativeis)
                .map(compativeis -> compativeis.isEmpty() ?
                        new ValidationResult(Severity.ERROR, "O item não é compatível com o grau do processo.") :
                        new ValidationResult());
    }

    private List<DetalhamentoSgt> findDetalhamentosCompativeis(List<DetalhamentoSgt> detalhamentosDaUnidadeJudiciaria,
                                                               Map<String, String> detalhamentosDoItem) {
        return detalhamentosDaUnidadeJudiciaria.stream()
                .filter(detalhamentoSgt -> "S".equals(detalhamentosDoItem.get(detalhamentoSgt.getKey())))
                .collect(Collectors.toList());
    }
}

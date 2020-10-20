package br.jus.cnj.inova.processo;

import br.jus.cnj.inova.resultado.ResultadoService;
import br.jus.cnj.inova.sgt.LocalItemSgt;
import br.jus.cnj.inova.sgt.SgtClientFacade;
import br.jus.cnj.inova.validators.Validation;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoPesquisaEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor
public class ProcessoTOConverter {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final SgtClientFacade sgtClient;
    private final ResultadoService resultadoService;

    public Mono<ProcessoTO> convert(Processo processo) {
        final var classe = getClasse(processo);
        final var processoTOBuilder = ProcessoTO.builder()
                .id(processo.getId())
                .npu(processo.getDadosBasicos().getNumero())
                .codigoClasse(processo.getDadosBasicos().getClasseProcessual())
                .descricaoClasse(this.getItemSgtName(classe))
                .dataDistribuicao(this.getDataDistribuicao(processo));
        return this.resultadoService.findById(processo.getId())
                .map(resultado -> processoTOBuilder
                        .validacoesRealizadas(resultado.getValidations().size())
                        .erros(this.getFailures(resultado.getValidations()).size())
                        .build())
                .switchIfEmpty(Mono.just(processoTOBuilder.build()));
    }

    private String getItemSgtName(Optional<LocalItemSgt> classe) {
        return classe.map(LocalItemSgt::getNome).orElse(null);
    }

    private List<ValidationResult> getFailures(List<Validation> validations) {
        return validations.stream()
                .map(Validation::getResult)
                .filter(ValidationResult::isError)
                .collect(Collectors.toList());
    }

    private LocalDate getDataDistribuicao(Processo processo) {
        try {
            return Optional.ofNullable(processo.getDadosBasicos().getDataAjuizamento())
                    .map(dataStr -> LocalDate.parse(dataStr, FORMATTER))
                    .orElse(null);
        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }

    private Optional<LocalItemSgt> getClasse(Processo processo) {
        try {
            return Optional.ofNullable(processo.getDadosBasicos().getClasseProcessual())
                    .map(String::valueOf)
                    .flatMap(this::getClasseSgt);
        } catch (Exception ex) {
            log.error(ex);
            return Optional.empty();
        }
    }

    private Optional<LocalItemSgt> getClasseSgt(String codigoClasse) {
        final var found = this.sgtClient
                .pesquisarItem(TipoItemEnum.CLASSE, TipoPesquisaEnum.CODIGO, codigoClasse);
        return found.isEmpty() ? Optional.empty() : Optional.of(found.iterator().next());
    }
}

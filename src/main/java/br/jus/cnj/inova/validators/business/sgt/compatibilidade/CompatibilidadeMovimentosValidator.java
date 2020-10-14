package br.jus.cnj.inova.validators.business.sgt.compatibilidade;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.processo.capa.Assunto;
import br.jus.cnj.inova.unidadejudiciaria.UnidadeJudiciaria;
import br.jus.cnj.inova.unidadejudiciaria.UnidadeJudiciariaService;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Validator(type = ValidatorType.CLASSES)
@RequiredArgsConstructor
public class CompatibilidadeMovimentosValidator implements ProcessoValidator {

    private final UnidadeJudiciariaService unidadeJudiciariaService;
    private final CompatibilidadeItemValidator compatibilidadeItemValidator;

    @Override
    public ValidationResult validate(Processo processo) {
        final var codigoOrgao = processo.getDadosBasicos().getOrgaoJulgador().getCodigoOrgao();
        return this.unidadeJudiciariaService.findByCodigo(String.valueOf(codigoOrgao))
                .flatMap(unidadeJudiciaria -> this.validate(processo, unidadeJudiciaria))
                .collectList()
                .map(causes -> new ValidationResult(Severity.ERROR,
                        "Existem assuntos incompatíveis com a unidade judiciária ou grau do processo", causes))
                .block();
    }

    private Flux<ValidationResult> validate(Processo processo, UnidadeJudiciaria unidadeJudiciaria) {
        return Flux.fromIterable(processo.getDadosBasicos().getAssunto())
                .map(Assunto::getCodigoNacional)
                .filter(Objects::nonNull)
                .flatMap(codigoAssunto -> this.compatibilidadeItemValidator.validate(
                        unidadeJudiciaria,
                        processo.getGrau(),
                        TipoItemEnum.ASSUNTO,
                        codigoAssunto))
                .onErrorResume(this::handleError);
    }

    private Mono<ValidationResult> handleError(Throwable error) {
        return Mono.just(new ValidationResult(Severity.ERROR,
                "Falha ao tentar validar a compatibilidade do movimento do processo. Causa: " + error));
    }
}

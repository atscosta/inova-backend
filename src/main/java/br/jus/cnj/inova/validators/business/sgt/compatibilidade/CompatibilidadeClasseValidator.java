package br.jus.cnj.inova.validators.business.sgt.compatibilidade;

import br.jus.cnj.inova.processo.Processo;
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

import java.util.Collections;

@Validator(type = ValidatorType.CLASSES)
@RequiredArgsConstructor
public class CompatibilidadeClasseValidator implements ProcessoValidator {

    private final UnidadeJudiciariaService unidadeJudiciariaService;
    private final CompatibilidadeItemValidator compatibilidadeItemValidator;

    @Override
    public ValidationResult validate(Processo processo) {
        final var codigoOrgao = processo.getDadosBasicos().getOrgaoJulgador().getCodigoOrgao();
        return this.unidadeJudiciariaService.findByCodigo(String.valueOf(codigoOrgao))
                .flatMap(unidadeJudiciaria -> this.validate(processo, unidadeJudiciaria))
                .map(cause -> new ValidationResult(Severity.ERROR,
                        "A classe não é compatível com a unidade judiciária ou grau do processo.",
                        Collections.singletonList(cause)))
                .onErrorResume(this::handleError)
                .next().block();
    }

    private Flux<ValidationResult> validate(Processo processo, UnidadeJudiciaria unidadeJudiciaria) {
        return this.compatibilidadeItemValidator.validate(
                unidadeJudiciaria,
                processo.getGrau(),
                TipoItemEnum.CLASSE,
                processo.getDadosBasicos().getClasseProcessual());
    }

    private Mono<ValidationResult> handleError(Throwable error) {
        return Mono.just(new ValidationResult(Severity.ERROR,
                "Falha ao tentar validar a compatibilidade da classe do processo. Causa: " + error));
    }
}

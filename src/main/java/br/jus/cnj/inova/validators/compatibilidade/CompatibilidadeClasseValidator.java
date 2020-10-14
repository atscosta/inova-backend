package br.jus.cnj.inova.validators.compatibilidade;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.unidadejudiciaria.UnidadeJudiciaria;
import br.jus.cnj.inova.unidadejudiciaria.UnidadeJudiciariaService;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class CompatibilidadeClasseValidator implements ProcessoValidator {

    private UnidadeJudiciariaService unidadeJudiciariaService;
    private CompatibilidadeItemValidator compatibilidadeItemValidator;

    @Override
    public ValidationResult validate(Processo processo) {
        final var codigoOrgao = processo.getDadosBasicos().getOrgaoJulgador().getCodigoOrgao();
        return this.unidadeJudiciariaService.findByCodigo(String.valueOf(codigoOrgao))
                .flatMap(unidadeJudiciaria -> validate(processo, unidadeJudiciaria)).next().block();
    }

    private Flux<ValidationResult> validate(Processo processo, UnidadeJudiciaria unidadeJudiciaria) {
        return this.compatibilidadeItemValidator.validate(
                unidadeJudiciaria,
                processo.getGrau(),
                TipoItemEnum.CLASSE,
                processo.getDadosBasicos().getClasseProcessual());
    }
}

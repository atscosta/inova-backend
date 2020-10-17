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
import lombok.extern.log4j.Log4j2;

@Log4j2
@Validator(type = ValidatorType.CLASSES)
@RequiredArgsConstructor
public class CompatibilidadeClasseValidator implements ProcessoValidator {

    private final UnidadeJudiciariaService unidadeJudiciariaService;
    private final CompatibilidadeItemValidator compatibilidadeItemValidator;

    @Override
    public ValidationResult validate(Processo processo) {
        final var codigoOrgao = processo.getDadosBasicos().getOrgaoJulgador().getCodigoOrgao();
        final var unidadeJudiciaria = this.unidadeJudiciariaService.findByCodigo(codigoOrgao);
        return this.validateSingle(processo, unidadeJudiciaria);
    }

    private ValidationResult validateSingle(Processo processo, UnidadeJudiciaria unidadeJudiciaria) {
        final var codigoClasse = processo.getDadosBasicos().getClasseProcessual();
        try {
            return this.compatibilidadeItemValidator.validate(
                    unidadeJudiciaria,
                    processo.getGrau(),
                    TipoItemEnum.CLASSE,
                    codigoClasse);
        } catch (Exception ex) {
            log.error(ex);
            return new ValidationResult(Severity.ERROR, "Falha ao tentar validar a classe: " +
                    codigoClasse + ". Causa: " + ex.getMessage());
        }
    }

    @Override
    public String getTitle() {
        return "A classe do processos deve ser compatível com sua unidade judiciária e grau.";
    }
}

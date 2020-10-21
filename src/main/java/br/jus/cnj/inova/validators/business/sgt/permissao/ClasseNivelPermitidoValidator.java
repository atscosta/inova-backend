package br.jus.cnj.inova.validators.business.sgt.permissao;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.sgt.SgtClientFacade;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.Severity;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import lombok.RequiredArgsConstructor;

@Validator(type = ValidatorType.CLASSES)
@RequiredArgsConstructor
public class ClasseNivelPermitidoValidator implements ProcessoValidator {

    private final SgtClientFacade sgtClient;

    @Override
    public ValidationResult validate(Processo processo) {
        final var codigoClasse = processo.getDadosBasicos().getClasseProcessual();
        return this.sgtClient.isUltimoNivel(codigoClasse, TipoItemEnum.CLASSE) ?
                new ValidationResult() :
                new ValidationResult(Severity.ERROR, "A classe " + codigoClasse + " não está no último nível.");
    }

    @Override
    public String getTitle() {
        return "As classes processuais devem estar no último nível da hierarquia.";
    }
    
    @Override
    public String getName() {
        return "ClasseNivelPermitido";
    }
}

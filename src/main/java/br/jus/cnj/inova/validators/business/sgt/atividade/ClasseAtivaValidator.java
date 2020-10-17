package br.jus.cnj.inova.validators.business.sgt.atividade;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.validators.ProcessoValidator;
import br.jus.cnj.inova.validators.ValidationResult;
import br.jus.cnj.inova.validators.Validator;
import br.jus.cnj.inova.validators.ValidatorType;
import br.jus.tjpb.libs.sgtsoapcient.pesquisaritem.TipoItemEnum;
import lombok.RequiredArgsConstructor;

@Validator(type = ValidatorType.CLASSES)
@RequiredArgsConstructor
public class ClasseAtivaValidator implements ProcessoValidator {

    private final AtividadeItemValidator atividadeItemValidator;

    @Override
    public ValidationResult validate(Processo processo) {
        return this.atividadeItemValidator.validate(TipoItemEnum.CLASSE, processo.getDadosBasicos().getClasseProcessual());
    }

    @Override
    public String getTitle() {
        return "A classe do processo deve estar ativa.";
    }
    
    @Override
    public String getName() {
        return "ClasseAtiva";
    }
}

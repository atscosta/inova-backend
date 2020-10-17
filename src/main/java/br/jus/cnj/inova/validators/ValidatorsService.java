package br.jus.cnj.inova.validators;

import br.jus.cnj.inova.processo.ProcessoService;
import br.jus.cnj.inova.tribunal.Tribunal;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ValidatorsService {

    private final ValidatorsManager validatorsManager;
    private final ProcessoService processoService;

    public List<ProcessoValidator> getAllValidators(){
        return this.validatorsManager.getAllValidators();
    }

    public void validate(Tribunal tribunal) {
        processoService.findAllBySigla(tribunal.getSigla());
    }
}

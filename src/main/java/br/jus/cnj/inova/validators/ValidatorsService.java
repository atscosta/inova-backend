package br.jus.cnj.inova.validators;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ValidatorsService {

    private final ValidatorsManager validatorsManager;

    public List<ProcessoValidator> getAllValidators(){
        return this.validatorsManager.getAllValidators();
    }
}

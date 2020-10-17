package br.jus.cnj.inova.validators;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ValidatorService {

    private final ValidatorsManager validatorsManager;

    List<ProcessoValidator> getAllValidators(){
        return this.validatorsManager.getAllValidators();
    }
    
    List<ProcessoValidator> getValidatorsByType(ValidatorType type) {
        return this.validatorsManager.getValidatorsByType(type);
    }
    
    List<ProcessoValidator> getAllByEnabledValidators(boolean enabled) {
        return this.validatorsManager.getAllValidators().stream()
            .filter(filterEnabled(enabled))
            .collect(Collectors.toList());
    }
    
    @NotNull
    private Predicate<ProcessoValidator> filterEnabled(boolean enabled) {
        return processoValidator -> processoValidator.isEnabled() == enabled;
    }
}

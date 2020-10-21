package br.jus.cnj.inova.validators;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ValidatorService {

    private final ValidatorsManager validatorsManager;

    public List<ProcessoValidator> getAllValidators(){
        return this.validatorsManager.getAllValidators();
    }
    
    public List<ProcessoValidator> getAllValidatorsByType(ValidatorType type) {
        return this.validatorsManager.getValidatorsByType(type);
    }
    
    public List<ProcessoValidator> getAllByEnabledValidators() {
        return this.getAllByEnabledValidators(true);
    }
    
    public List<ProcessoValidator> getAllByEnabledValidators(boolean enabled) {
        return this.validatorsManager.getAllValidators().parallelStream()
            .filter(filterByEnabled(enabled))
            .collect(Collectors.toList());
    }
    
    void enableValidator(@NotNull String name, Boolean enabled) {
        this.validatorsManager.getAllValidators().parallelStream()
            .filter(filterByName(name))
            .forEach(setEnabledProcessoValidator(enabled));
    }
    
    ProcessoValidator findOneByName(@NotNull String name) {
        return this.validatorsManager.getAllValidators().parallelStream()
            .filter(filterByName(name))
            .findFirst()
            .orElseThrow();
    }
    
    @NotNull
    private Consumer<ProcessoValidator> setEnabledProcessoValidator(Boolean enabled) {
        return processoValidator -> processoValidator.setEnabled(enabled);
    }
    
    @NotNull
    private Predicate<ProcessoValidator> filterByEnabled(boolean enabled) {
        return processoValidator -> processoValidator.isEnabled() == enabled;
    }
    
    @NotNull
    private Predicate<ProcessoValidator> filterByName(String name) {
        return processoValidator -> name.equalsIgnoreCase(processoValidator.getName());
    }
}

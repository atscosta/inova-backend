package br.jus.cnj.inova.validators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ValidatorsManager {

    private final Map<ValidatorType, List<ProcessoValidator>> validatorsMap = new EnumMap<>(ValidatorType.class);

    void register(ProcessoValidator validator) {
        final var type = validator.getValidatorType();
        final List<ProcessoValidator> validatorsList = Optional.ofNullable(this.validatorsMap.get(type))
                .orElseGet(ArrayList::new);
        validatorsList.add(validator);
        this.validatorsMap.put(type, validatorsList);
    }

    List<ProcessoValidator> getValidatorsByType(ValidatorType type) {
        return this.validatorsMap.get(type);
    }

    List<ProcessoValidator> getAllValidators() {
        return this.validatorsMap.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}

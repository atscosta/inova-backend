package br.jus.cnj.inova.validators;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorsConfig {

    @Bean
    ValidatorsManager getValidatorsFactory() {
        final var validatorsManager = new ValidatorsManager();
        validatorsManager.register(ValidatorType.UNIDADE_JUDICIARIA, new PrimeiraValidacao());
        validatorsManager.register(ValidatorType.UNIDADE_JUDICIARIA, new SegundaValidacao());
        validatorsManager.register(ValidatorType.UNIDADE_JUDICIARIA, new SegundaValidacao());
        return validatorsManager;
    }
}

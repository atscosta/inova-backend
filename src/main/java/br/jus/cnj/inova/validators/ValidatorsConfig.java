package br.jus.cnj.inova.validators;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorsConfig {

    @Bean
    ValidatorsManager getValidatorsManager(ApplicationContext context) {
        final var validatorsManager = new ValidatorsManager();
        context.getBeansWithAnnotation(Validator.class).values().stream()
                .map(bean -> (ProcessoValidator) bean)
                .forEach(validatorsManager::register);
        return validatorsManager;
    }
    
}

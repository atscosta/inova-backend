package br.jus.cnj.inova.validators;

import br.jus.cnj.inova.validators.compatibilidade.CompatibilidadeClasseValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorsConfig {

    @Bean
    ValidatorsManager getValidatorsFactory(CompatibilidadeClasseValidator compatibilidadeClasseValidator) {
        final var validatorsManager = new ValidatorsManager();
        validatorsManager.register(ValidatorType.CLASSE, compatibilidadeClasseValidator);
        return validatorsManager;
    }
}

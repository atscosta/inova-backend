package br.jus.cnj.inova.validators.business;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorsConfig {

    @Bean
    ValidatorsManager getValidatorsFactory()
        throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final var validatorsManager = new ValidatorsManager();
    
        for (Class<?> aClass : getValidatorsClasses()) {
            validatorsManager.register(getValidatorType(aClass), newInstance(aClass));
        }
    
        return validatorsManager;
    }
    
    // Aqui poderia criar uma nova anotação indicando quais pacotes deveriam ser escaneados,
    // a fim de permitir a expansão dos validadores
    private Set<Class<?>> getValidatorsClasses() {
        final Reflections reflections = new Reflections("br.jus.cnj.inova.validators");
        return reflections.getTypesAnnotatedWith(Validator.class);
    }
    
    @NotNull
    private ProcessoValidator newInstance(Class<?> aClass)
        throws InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        return (ProcessoValidator) aClass.getConstructor().newInstance();
    }
    
    @NotNull
    private ValidatorType getValidatorType(Class<?> aClass) {
        return aClass.getAnnotation(Validator.class).type();
    }
}

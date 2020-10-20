package br.jus.cnj.inova.validators;

import br.jus.cnj.inova.processo.Processo;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Map;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

public interface ProcessoValidator {
    
    ValidationResult validate(Processo processo);
    
    String getTitle();
    
    default String getName() {
        return this.getClass().getSimpleName().replace("Validator", "");
    }
    
    default ValidatorType getValidatorType() {
        return this.getClass().getAnnotation(Validator.class).type();
    }
    
    default Boolean isEnabled() {
        return this.getClass().getAnnotation(Validator.class).enabled();
    }
    
    default void setEnabled(Boolean enabled) {
        final var annotation = this.getClass().getAnnotation(Validator.class);
        changeAnnotationValue(annotation, "enabled", enabled);
    }
    
    /**
     * Changes the annotation value for the given key of the given annotation to newValue and returns the previous
     * value.
     */
    @SneakyThrows
    @SuppressWarnings("unchecked")
    private static void changeAnnotationValue(Annotation annotation, String key, @NotNull Boolean newValue) {
        Object handler = Proxy.getInvocationHandler(annotation);
        Field f = handler.getClass().getDeclaredField("memberValues");
        f.setAccessible(true);
        Map<String, Object> memberValues = (Map<String, Object>) f.get(handler);
        memberValues.put(key, newValue);
    }
    
}

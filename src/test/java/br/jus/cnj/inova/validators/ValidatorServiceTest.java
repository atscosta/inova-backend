package br.jus.cnj.inova.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValidatorServiceTest {
    
    @Autowired
    private ValidatorService service;
    
    @Test
    void enableValidator() {
        service.enableValidator("NPU", false);
        final var enabled = service.findOneByName("NPU").isEnabled();
        assertFalse(enabled);
    }
    
    @Test
    void findByTipo() {
        final var validatorsAssunto = service.getValidatorsByType(ValidatorType.ASSUNTOS);
        assertTrue(validatorsAssunto.stream().allMatch(v-> ValidatorType.ASSUNTOS.equals(v.getValidatorType())));
    }
    
    @Test
    void findOneByName() {
        final var camposObrigatorios = service.findOneByName("camposobrigatorios");
        assertEquals(ValidatorType.FIELD, camposObrigatorios.getValidatorType());
    }
}

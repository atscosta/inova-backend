package br.jus.cnj.inova.validators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
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
    void listAllValidators() {
        final var allValidators = service.getAllValidators();
        allValidators.forEach(v -> System.out.println(v.getName()));
    }
    
    @Test
    void findByTipo() {
        final var validatorsAssunto = service.getValidatorsByType(ValidatorType.ASSUNTOS);
        validatorsAssunto.forEach(v -> System.out.println(v.getName()));
    }
    
    @Test
    void findOneByName() {
        service.findOneByName("CamposObrigatorios");
    }
}

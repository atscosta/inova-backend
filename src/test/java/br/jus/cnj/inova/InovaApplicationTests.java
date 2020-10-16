package br.jus.cnj.inova;

import br.jus.cnj.inova.processo.resultado.ResultadoService;
import br.jus.cnj.inova.validators.ValidatorsManager;
import br.jus.cnj.inova.validators.ValidatorsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InovaApplicationTests {
    
    @Autowired
    private ValidatorsManager validatorsManager;
    
    @Autowired
    private ResultadoService resultadoService;
    
    @Test
    void contextLoads() {
        ValidatorsService service = new ValidatorsService(validatorsManager, resultadoService);
        service.getAllValidators().forEach(validator -> System.out.println(validator.getClass().getCanonicalName()));
    }
}

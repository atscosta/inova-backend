package br.jus.cnj.inova;

import br.jus.cnj.inova.resultados.ResultadosService;
import br.jus.cnj.inova.validators.business.ValidatorsManager;
import br.jus.cnj.inova.validators.business.ValidatorsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InovaApplicationTests {
    
    @Autowired
    private ValidatorsManager validatorsManager;
    
    @Autowired
    private ResultadosService resultadosService;
    
    @Test
    void contextLoads() {
        ValidatorsService service = new ValidatorsService(validatorsManager, resultadosService);
        service.getAllValidators().forEach(validator -> System.out.println(validator.getClass().getCanonicalName()));
    }
}

package br.jus.cnj.inova.validators;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validators")
@RequiredArgsConstructor
public class ValidatorRestController {

    private final ValidatorService service;
    
    @GetMapping
    public List<ProcessoValidator> listarTodos() {
        return service.getAllValidators();
    }
    
    @PutMapping("/{}")
    public List<ProcessoValidator> listarHabilitados(@RequestParam Boolean enabled) {
        return service.getAllByEnabledValidators(enabled);
    }
    
    @GetMapping(params = "type")
    public List<ProcessoValidator> listarPorTipo(@RequestParam ValidatorType type) {
        return service.getValidatorsByType(type);
    }
    
}

package br.jus.cnj.inova.validators;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/validadores")
public class ValidatorRestController {
    
    private final ValidatorService service;
    
    @GetMapping
    public List<ProcessoValidator> listarTodos() {
        return this.service.getAllValidators();
    }
    
    @GetMapping(params = "enabled")
    public List<ProcessoValidator> listarHabilitados(@RequestParam Boolean enabled) {
        return this.service.getAllByEnabledValidators(enabled);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{name}")
    public void listarHabilitados(@PathVariable String name, @RequestParam Boolean enabled) {
        this.service.enableValidator(name, enabled);
    }
    
    @GetMapping("/{name}")
    public ProcessoValidator listarPorTipo(@PathVariable String name) {
        return this.service.findOneByName(name);
    }
    
    @GetMapping(params = "type")
    public List<ProcessoValidator> listarPorTipo(@RequestParam ValidatorType type) {
        return this.service.getAllValidatorsByType(type);
    }
    
}

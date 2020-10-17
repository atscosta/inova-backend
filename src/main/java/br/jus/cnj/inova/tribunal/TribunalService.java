package br.jus.cnj.inova.tribunal;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TribunalService {

    private final TribunalRepository repository;

    Flux<Tribunal> findAll() {
        return this.repository.findAll();
    }

    Flux<Tribunal> findByCodigoJustica(String codigoJustica) {
        return this.repository.findByCodigoJustica(codigoJustica);
    }

    Flux<Tribunal> findByUf(String uf) {
        return this.repository.findByUf(uf);
    }

    public Mono<Tribunal> findBySigla(Mono<String> sigla) {
        return this.repository.findBySigla(sigla);
    }

    public Tribunal findByCodigo(String codigo) {
        return this.repository.findFirstByCodigo(codigo);
    }
}

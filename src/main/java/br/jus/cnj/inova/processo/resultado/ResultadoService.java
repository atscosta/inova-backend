package br.jus.cnj.inova.processo.resultado;

import br.jus.cnj.inova.processo.Processo;
<<<<<<< HEAD
import br.jus.cnj.inova.processo.ProcessoService;
import br.jus.cnj.inova.processo.capa.DadosBasicos;
import br.jus.cnj.inova.processo.capa.OrgaoJulgador;
import br.jus.cnj.inova.validators.Validation;
import br.jus.cnj.inova.validators.ValidationResult;
=======
import br.jus.cnj.inova.processo.capa.DadosBasicos;
import br.jus.cnj.inova.processo.capa.OrgaoJulgador;
import br.jus.cnj.inova.validators.Validation;
>>>>>>> 0f29c97888cd02ce40b3f361411a43c184008c9e
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

<<<<<<< HEAD
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;
=======
import java.util.Optional;
>>>>>>> 0f29c97888cd02ce40b3f361411a43c184008c9e


@Service
@RequiredArgsConstructor
public class ResultadoService {

    private final ResultadoRepository repository;

<<<<<<< HEAD
    public Mono<Resultado> save(Processo processo, Set<Validation> validations) {
        return this.save(Mono.just(processo), validations);
    }

    public Mono<Resultado> save(Mono<Processo> processoMono, Set<Validation> validations) {
        return processoMono.map(p -> new Resultado(p, validations))
=======
    public Mono<Resultado> save(Mono<Processo> processo, Validation validation) {
        return processo.map(p -> createResultado(validation, p))
>>>>>>> 0f29c97888cd02ce40b3f361411a43c184008c9e
                .flatMap(repository::save);
    }

    public Mono<Resultado> findById(String idProcesso) {
        return this.repository.findById(idProcesso);
    }

    public Flux<Resultado> findAll() {
        return repository.findAll();
    }
<<<<<<< HEAD

=======
    @NotNull
    private Resultado createResultado(Validation validation, Processo processo) {
        Resultado resultado = new Resultado(processo);

        Optional<DadosBasicos> dadosBasicosOptional = Optional.of(processo.getDadosBasicos());

        final String numeroProcesso = dadosBasicosOptional
                .map(DadosBasicos::getNumero)
                .orElse(null);

        final Long codClasse = dadosBasicosOptional
                .map(DadosBasicos::getClasseProcessual)
                .orElse(null);

        final Long codOrgaoJulgador = dadosBasicosOptional
                .map(DadosBasicos::getOrgaoJulgador)
                .map(OrgaoJulgador::getCodigoOrgao)
                .orElse(null);

        resultado.setClasseProcessual(codClasse);
        resultado.setNumero(numeroProcesso);
        resultado.setCodOrgaoJulgador(codOrgaoJulgador);
        resultado.setSiglaTribunal(processo.getSiglaTribunal());

        resultado.addValidation(validation);
        return resultado;
    }
>>>>>>> 0f29c97888cd02ce40b3f361411a43c184008c9e
}

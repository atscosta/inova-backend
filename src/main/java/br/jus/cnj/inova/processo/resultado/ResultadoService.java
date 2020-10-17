package br.jus.cnj.inova.processo.resultado;

import br.jus.cnj.inova.processo.Processo;
import br.jus.cnj.inova.processo.capa.DadosBasicos;
import br.jus.cnj.inova.processo.capa.OrgaoJulgador;
import br.jus.cnj.inova.validators.Validation;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ResultadoService {

    private final ResultadoRepository repository;

    public Mono<Resultado> save(Mono<Processo> processo, Validation validation) {
        return processo.map(p -> createResultado(validation, p))
                .flatMap(repository::save);
    }

    public Mono<Resultado> findById(String idProcesso) {
        return this.repository.findById(idProcesso);
    }

    public Flux<Resultado> findAll() {
        return repository.findAll();
    }
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
}

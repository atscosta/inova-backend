package br.jus.cnj.inova.validators.field.npu;

public class NPUInvalidoException extends RuntimeException {

    public NPUInvalidoException(String numeroProcesso) {
        super("Número do processo inválido: " + numeroProcesso);
    }
}

package br.jus.cnj.inova.validations;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@EqualsAndHashCode
public class NPU {
    
    private static final int SEQUENCIA_LEN = 7;
    private static final int DIGITO_VERIFICADOR_LEN = 2;
    private static final int ANO_LEN = 4;
    private static final int IDENTIFICADOR_ORGAO_JUSTICA_LEN = 3;
    private static final int ORIGEM_PROCESSO_LEN = 4;
    private static final int TOTAL_LENGTH = 20;
    private static final String NPU_REGEX = "-?\\d+(\\.\\d+)?";
    
    private Long sequencia;
    private Integer digitoVerificador;
    private Integer ano;
    private Integer identificacaoOrgaoJustica;
    private Integer origemProcesso;
    
    public NPU(String npu) {
        validate(npu);
        
        this.setSequencia(npu);
        this.setDigitoVerificador(npu);
        this.setAno(npu);
        this.setIdentificacaoOrgaoJustica(npu);
        this.setOrigemProcesso(npu);
    }
    
    private static void validate(String npu) {
        if (!isValid(npu)) {
            throw new RuntimeException("NPU inválido.");
        }
    }
    
    public static boolean isValid(String npu) {
        return StringUtils.isNotBlank(npu) && npu.length() == TOTAL_LENGTH && npu.matches(NPU_REGEX);
    }
    
    private void setSequencia(String npu) {
        val sequenciaSubstr = npu.substring(0, SEQUENCIA_LEN);
        this.sequencia = Long.parseLong(sequenciaSubstr);
    }
    
    private void setDigitoVerificador(String npu) {
        val digitoVerificadorSubstr = npu.substring(SEQUENCIA_LEN, SEQUENCIA_LEN + DIGITO_VERIFICADOR_LEN);
        this.digitoVerificador = Integer.parseInt(digitoVerificadorSubstr);
    }
    
    private void setAno(String npu) {
        val offset = SEQUENCIA_LEN + DIGITO_VERIFICADOR_LEN;
        val anoSubstr = npu.substring(offset, offset + ANO_LEN);
        this.ano = Integer.parseInt(anoSubstr);
    }
    
    private void setIdentificacaoOrgaoJustica(String npu) {
        val offset = SEQUENCIA_LEN + DIGITO_VERIFICADOR_LEN + ANO_LEN;
        val numeroIdentificacaoOrgaoJusticaSubstr =
            npu.substring(offset, offset + IDENTIFICADOR_ORGAO_JUSTICA_LEN);
        this.identificacaoOrgaoJustica = Integer.parseInt(numeroIdentificacaoOrgaoJusticaSubstr);
    }
    
    private void setOrigemProcesso(String npu) {
        val offset = SEQUENCIA_LEN + DIGITO_VERIFICADOR_LEN + ANO_LEN + IDENTIFICADOR_ORGAO_JUSTICA_LEN;
        val numeroOrigemProcessoSubstr = npu.substring(offset, offset + ORIGEM_PROCESSO_LEN);
        this.origemProcesso = Integer.parseInt(numeroOrigemProcessoSubstr);
    }
    
    @Override
    public String toString() {
        return zerolpad(String.valueOf(this.sequencia), SEQUENCIA_LEN)
            + zerolpad(String.valueOf(this.digitoVerificador), DIGITO_VERIFICADOR_LEN)
            + zerolpad(String.valueOf(this.ano), ANO_LEN)
            + zerolpad(String.valueOf(this.identificacaoOrgaoJustica), IDENTIFICADOR_ORGAO_JUSTICA_LEN)
            + zerolpad(String.valueOf(this.origemProcesso), ORIGEM_PROCESSO_LEN);
    }
    
    private static String zerolpad(String str, Integer len) {
        return StringUtils.leftPad(str, len, '0');
    }
}

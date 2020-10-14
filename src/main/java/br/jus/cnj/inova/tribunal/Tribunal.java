package br.jus.cnj.inova.tribunal;

public enum Tribunal {

    CNJ("Conselho Nacional de Justiça", "SUPERIOR"),
    STJ("Superior Tribunal de Justiça", "SUPERIOR"),
    TST("Tribunal Superior do Trabalho", "SUPERIOR"),
    TSE("Tribunal Superior Eleitoral", "SUPERIOR"),
    STM("Superior Tribunal Militar", "SUPERIOR"),

    TREAC("Tribunal Regional Eleitoral do Acre", "Tribunal Regional Eleitoral"),
    TREAL("Tribunal Regional Eleitoral de Alagoas", "Tribunal Regional Eleitoral"),
    TREAM("Tribunal Regional Eleitoral do Amazonas", "Tribunal Regional Eleitoral"),
    TREAP("Tribunal Regional Eleitoral do Amapá", "Tribunal Regional Eleitoral"),
    TREBA("Tribunal Regional Eleitoral da Bahia", "Tribunal Regional Eleitoral"),
    TRECE("Tribunal Regional Eleitoral do Ceará", "Tribunal Regional Eleitoral"),
    TREDF("Tribunal Regional Eleitoral do Distrito Federal", "Tribunal Regional Eleitoral"),
    TREES("Tribunal Regional Eleitoral do Espírito Santo", "Tribunal Regional Eleitoral"),
    TREGO("Tribunal Regional Eleitoral de Goiás", "Tribunal Regional Eleitoral"),
    TREMA("Tribunal Regional Eleitoral do Maranhão", "Tribunal Regional Eleitoral"),
    TREMG("Tribunal Regional Eleitoral de Minas Gerais", "Tribunal Regional Eleitoral"),
    TREMS("Tribunal Regional Eleitoral do Mato Grosso do Sul", "Tribunal Regional Eleitoral"),
    TREMT("Tribunal Regional Eleitoral do Mato Grosso", "Tribunal Regional Eleitoral"),
    TREPA("Tribunal Regional Eleitoral do Pará", "Tribunal Regional Eleitoral"),
    TREPB("Tribunal Regional Eleitoral da Paraíba", "Tribunal Regional Eleitoral"),
    TREPE("Tribunal Regional Eleitoral de Pernambuco", "Tribunal Regional Eleitoral"),
    TREPI("Tribunal Regional Eleitoral do Piauí", "Tribunal Regional Eleitoral"),
    TREPR("Tribunal Regional Eleitoral do Paraná", "Tribunal Regional Eleitoral"),
    TRERJ("Tribunal Regional Eleitoral do Rio de Janeiro", "Tribunal Regional Eleitoral"),
    TRERN("Tribunal Regional Eleitoral do Rio Grande do Norte", "Tribunal Regional Eleitoral"),
    TRERO("Tribunal Regional Eleitoral de Rondônia", "Tribunal Regional Eleitoral"),
    TRERR("Tribunal Regional Eleitoral de Roraima", "Tribunal Regional Eleitoral"),
    TRERS("Tribunal Regional Eleitoral do Rio Grande do Sul", "Tribunal Regional Eleitoral"),
    TRESC("Tribunal Regional Eleitoral de Santa Catarina", "Tribunal Regional Eleitoral"),
    TRESE("Tribunal Regional Eleitoral de Sergipe", "Tribunal Regional Eleitoral"),
    TRESP("Tribunal Regional Eleitoral de São Paulo", "Tribunal Regional Eleitoral"),
    TRETO("Tribunal Regional Eleitoral de Tocantins", "Tribunal Regional Eleitoral"),

    TJAC("Tribunal de Justiça do Estado do Acre", "Tribunal de Justiça"),
    TJAL("Tribunal de Justiça do Estado de Alagoas", "Tribunal de Justiça"),
    TJAM("Tribunal de Justiça do Estado do Amazonas", "Tribunal de Justiça"),
    TJAP("Tribunal de Justiça do Estado do Amapá", "Tribunal de Justiça"),
    TJBA("Tribunal de Justiça do Estado da Bahia", "Tribunal de Justiça"),
    TJCE("Tribunal de Justiça do Estado do Ceará", "Tribunal de Justiça"),
    TJDFT("Tribunal de Justiça do Distrito Federal", "Tribunal de Justiça"),
    TJES("Tribunal de Justiça do Estado do Espírito Santo", "Tribunal de Justiça"),
    TJGO("Tribunal de Justiça do Estado de Goiás", "Tribunal de Justiça"),
    TJMA("Tribunal de Justiça do Estado do Maranhão", "Tribunal de Justiça"),
    TJMG("Tribunal de Justiça do Estado de Minas Gerais", "Tribunal de Justiça"),
    TJMS("Tribunal de Justiça do Estado de Mato Grosso do Sul", "Tribunal de Justiça"),
    TJMT("Tribunal de Justiça do Estado de Mato Grosso", "Tribunal de Justiça"),
    TJPA("Tribunal de Justiça do Estado do Pará", "Tribunal de Justiça"),
    TJPB("Tribunal de Justiça do Estado da Paraíba", "Tribunal de Justiça"),
    TJPE("Tribunal de Justiça do Estado de Pernambuco", "Tribunal de Justiça"),
    TJPI("Tribunal de Justiça do Estado do Piauí", "Tribunal de Justiça"),
    TJPR("Tribunal de Justiça do Estado do Paraná", "Tribunal de Justiça"),
    TJRJ("Tribunal de Justiça do Estado do Rio de Janeiro", "Tribunal de Justiça"),
    TJRN("Tribunal de Justiça do Estado do Rio Grande do Norte", "Tribunal de Justiça"),
    TJRO("Tribunal de Justiça do Estado de Rondônia", "Tribunal de Justiça"),
    TJRR("Tribunal de Justiça do Estado de Roraima", "Tribunal de Justiça"),
    TJRS("Tribunal de Justiça do Estado do Rio Grande do Sul", "Tribunal de Justiça"),
    TJSC("Tribunal de Justiça do Estado de Santa Catarina", "Tribunal de Justiça"),
    TJSE("Tribunal de Justiça do Estado de Sergipe", "Tribunal de Justiça"),
    TJSP("Tribunal de Justiça do Estado de São Paulo", "Tribunal de Justiça"),
    TJTO("Tribunal de Justiça do Estado de Tocantins", "Tribunal de Justiça"),

    TRT1("Tribunal Regional do Trabalho da 1ª Região", "Tribunal Regional do Trabalho"),
    TRT2("Tribunal Regional do Trabalho da 2ª Região", "Tribunal Regional do Trabalho"),
    TRT3("Tribunal Regional do Trabalho da 3ª Região", "Tribunal Regional do Trabalho"),
    TRT4("Tribunal Regional do Trabalho da 4ª Região", "Tribunal Regional do Trabalho"),
    TRT5("Tribunal Regional do Trabalho da 5ª Região", "Tribunal Regional do Trabalho"),
    TRT6("Tribunal Regional do Trabalho da 6ª Região", "Tribunal Regional do Trabalho"),
    TRT7("Tribunal Regional do Trabalho da 7ª Região", "Tribunal Regional do Trabalho"),
    TRT8("Tribunal Regional do Trabalho da 8ª Região", "Tribunal Regional do Trabalho"),
    TRT9("Tribunal Regional do Trabalho da 9ª Região", "Tribunal Regional do Trabalho"),
    TRT10("Tribunal Regional do Trabalho da 10ª Região", "Tribunal Regional do Trabalho"),
    TRT11("Tribunal Regional do Trabalho da 11ª Região", "Tribunal Regional do Trabalho"),
    TRT12("Tribunal Regional do Trabalho da 12ª Região", "Tribunal Regional do Trabalho"),
    TRT13("Tribunal Regional do Trabalho da 13ª Região", "Tribunal Regional do Trabalho"),
    TRT14("Tribunal Regional do Trabalho da 14ª Região", "Tribunal Regional do Trabalho"),
    TRT15("Tribunal Regional do Trabalho da 15ª Região", "Tribunal Regional do Trabalho"),
    TRT16("Tribunal Regional do Trabalho da 16ª Região", "Tribunal Regional do Trabalho"),
    TRT17("Tribunal Regional do Trabalho da 17ª Região", "Tribunal Regional do Trabalho"),
    TRT18("Tribunal Regional do Trabalho da 18ª Região", "Tribunal Regional do Trabalho"),
    TRT19("Tribunal Regional do Trabalho da 19ª Região", "Tribunal Regional do Trabalho"),
    TRT20("Tribunal Regional do Trabalho da 20ª Região", "Tribunal Regional do Trabalho"),
    TRT21("Tribunal Regional do Trabalho da 21ª Região", "Tribunal Regional do Trabalho"),
    TRT22("Tribunal Regional do Trabalho da 22ª Região", "Tribunal Regional do Trabalho"),
    TRT23("Tribunal Regional do Trabalho da 23ª Região", "Tribunal Regional do Trabalho"),
    TRT24("Tribunal Regional do Trabalho da 24ª Região", "Tribunal Regional do Trabalho"),

    TRF1("Tribunal Regional Federal da 1ª Região", "Tribunal Regional Federal"),
    TRF2("Tribunal Regional Federal da 2ª Região", "Tribunal Regional Federal"),
    TRF3("Tribunal Regional Federal da 3ª Região", "Tribunal Regional Federal"),
    TRF4("Tribunal Regional Federal da 4ª Região", "Tribunal Regional Federal"),
    TRF5("Tribunal Regional Federal da 5ª Região", "Tribunal Regional Federal");

    private final String descricao;
    private final String classificacao;

    Tribunal(String descricao, String classificacao) {
        this.descricao = descricao;
        this.classificacao = classificacao;
    }

    public String descricao() {
        return this.descricao;
    }

    public String classificacao() {
        return this.classificacao;
    }
}

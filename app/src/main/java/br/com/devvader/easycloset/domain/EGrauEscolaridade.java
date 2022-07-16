package br.com.devvader.easycloset.domain;

public enum EGrauEscolaridade {

    ESCOLHER_ESCOLARIDADE("Escolher escolaridade"),
    DOUTORADO("Doutorado"),
    MESTRADO("Mestrado"),
    ESPECIALIZACAO_OU_MBA("Especialização ou MBA"),
    SUPERIOR("Ensino Superior"),
    ENSINO_TECNICO("Ensino Técnico"),
    ENSINO_MEDIO("Ensino Médio"),
    ENSINO_FUNDAMENTAL("Ensino Fundamental"),
    ENSINO_FUNDAMENTAL_INCOMPLETO("Ensino Fundamental Incompleto");

    private String valor;

    EGrauEscolaridade(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}

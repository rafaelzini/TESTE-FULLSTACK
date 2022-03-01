package com.zini.locadora.enums;

public enum GeneroEnum {


    TERROR("Terror"),
    AVENTURA("Aventura"),
    ROMANCE("Romance"),
    FICCAO("Ficção Científica"),
    FANTASIA("Fantasia"),
    SUSPENSE("Suspense");

    String descricao;

    GeneroEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

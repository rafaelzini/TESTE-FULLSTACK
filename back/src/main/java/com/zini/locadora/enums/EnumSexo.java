package com.zini.locadora.enums;

public enum EnumSexo {

    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    NAO_IDENFICAR("Não Identificar");

    String descricao;

    EnumSexo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}

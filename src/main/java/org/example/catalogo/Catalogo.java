package org.example.catalogo;

public enum Catalogo {
    SETTORE("settore"),
    ARGOMENTO("argomento"),
    AREA_GEOGRAFICA("area geografica");

    private final String nome;
    Catalogo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

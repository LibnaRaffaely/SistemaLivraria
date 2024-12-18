package com.sistemalivraria.models.enums;

public enum Genero {
    COMEDIA("comédia"),
    ROMANCE("romance"),
    BIOGRAFIA("biografia"),
    SUSPENSE("suspense"),
    DRAMA("drama"),
    SCI_FI("sci-Fi"),
    INFANTIL("infantil"),
    FANTASIA("fantasia");

    private String nome;

    public static Genero fromString(String nomeGenero) {
        for (Genero genero : Genero.values()) {
            if (genero.getNome().equalsIgnoreCase(nomeGenero)) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Gênero inválido: " + nomeGenero);
    }

    Genero(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

package com.sistemalivraria.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Autor {
    private static int idAuxiliar = 1;
    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private List<Livro> livrosAutoria;
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public Autor(LocalDate dataNascimento, String nome) {
        this.id = idAuxiliar;
        idAuxiliar++;
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.livrosAutoria = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Livro> getLivrosAutoria() {
        return livrosAutoria;
    }

    @Override
    public String toString() {
        String livros = " ";
        for(Livro livroAux : livrosAutoria){
            livros = livros.concat(livroAux.getTitulo());
            livros = livros.concat("\n");
        }
        if(livros.equals(" ")){
            livros = null;
        }



        return "Autor " + id +
                "\n nome:'" + nome + '\'' +
                "\n dataNascimento:" + dataNascimento.format(formato) +
                "\n livrosAutoria:" + livros ;
    }
}

package com.sistemalivraria.models;

import com.sistemalivraria.models.enums.Genero;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Livro {
    private static int idAuxiliar = 1;
    private int id;
    private String titulo;
    private Autor autor;
    private Genero genero;
    /** variavel responsável por mostrar se o livro está disponivel
     * <ol> true: disponível</ol>
     * <ol> false: indisponível</ol>
     */
    private  boolean disponibilidade;
    private LocalDate dataCadastro;
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Livro(String titulo, Autor autor,LocalDate dataCadastro, Genero genero) {
        this.id = idAuxiliar;
        idAuxiliar++;
        this.titulo = titulo;
        this.autor = autor;
        this.disponibilidade = true;
        this.dataCadastro = dataCadastro;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public  Genero getGenero(){
        return genero;
    }

    @Override
    public String toString() {
        String disponivel = null;
        if(disponibilidade){
             disponivel = "Disponivel";
        }else{
            disponivel = "Indisponivel";
        }
        return "Livro " + id +
                "\n titulo:'" + titulo  +
                "\n autor:" + autor +
                "\n genero=" + genero.getNome() +
                "\n disponibilidade:" + disponivel +
                "\n dataCadastro:" + dataCadastro.format(formato) + "\n\n";
    }
}

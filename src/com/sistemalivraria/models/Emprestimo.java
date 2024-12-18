package com.sistemalivraria.models;

import java.time.LocalDate;

public class Emprestimo {
    private static int idAuxiliar = 1;
    private int id;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private Cliente cliente;
    private boolean devolvido;

    public Emprestimo(Livro livro, LocalDate dataEmprestimo, Cliente cliente) {
        this.id = idAuxiliar;
        idAuxiliar ++;
        this.livro = livro;
        this.cliente = cliente;
        this.dataEmprestimo = dataEmprestimo;
        this.devolvido = false;
    }
    //metodo para devolucao

    public void realizarDevolucao(){
        livro.setDisponibilidade(true);
        dataDevolucao = LocalDate.now();
        devolvido = true;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean getDevolvido(){
        return devolvido;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
}

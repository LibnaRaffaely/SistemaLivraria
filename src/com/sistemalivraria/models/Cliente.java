package com.sistemalivraria.models;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private static int idAuxiliar = 1;
    private int id;
    private String nome;
    private Long contato;
    private String cpf;
    private List<Emprestimo> emprestimosCliente;

    public Cliente(String nome, String cpf,Long contato) {
        this.id = idAuxiliar;
        idAuxiliar++;
        this.nome = nome;
        this.contato = contato;
        this.cpf = cpf;
        emprestimosCliente = new ArrayList<>();
    }

    //construir um metodo regex para gerenciar a validade do email

    //metodo de armazenagem de empréstimos na List
    //

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

    public Long getContato() {
        return contato;
    }

    public void setContato(Long contato) {
        this.contato = contato;
    }

    public List<Emprestimo> getEmprestimosCliente() {
        return emprestimosCliente;
    }

    public void setEmprestimosCliente(List<Emprestimo> emprestimosCliente) {
        this.emprestimosCliente = emprestimosCliente;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {

        return "Cliente " + id +
                "\n nome: " + nome +
                "\n contato: " + String.valueOf(contato)+
                "\n cpf: " + cpf  ;
    }
}

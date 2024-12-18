package com.sistemalivraria.models;

import com.sistemalivraria.models.enums.Genero;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Livraria {
    private List<Livro> livros;
    private List<Autor> autores;
    private List<Emprestimo> emprestimos;
    private List<Cliente> clientes;

    public Livraria() {
        this.livros = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    //metodo para encontrar o livro no arsenal
    public Livro selecionarLivro(String titulo,String autor){
        for(Livro livroAux : livros){
            if(livroAux.getTitulo().equalsIgnoreCase(titulo) && livroAux.getAutor().getNome().equalsIgnoreCase(autor)){
                return livroAux;
            }
        }
        return null;
    }

    public Cliente selecionarCliente(String nome,String cpf){
        for(Cliente clienteAux : clientes){
            if(clienteAux.getNome().equalsIgnoreCase(nome) && clienteAux.getCpf().equals(cpf)){
                return clienteAux;
            }
        }
        return null;
    }

    public Autor selecionarAutor(String nome){
        for(Autor AutorAux : autores){
            if(AutorAux.getNome().equalsIgnoreCase(nome) ){
                return AutorAux;
            }
        }
        return null;
    }


    /**Metodo responsavel por filtrar de acordo com o titulo e nome do Autor
     * <ol> nesse metodo é possivel passar os paramentros nullos, dessa forma temos uma analise da nulalidade e caso nao seja nulo é  comparado os valores das strings com os titulos e autores de cada livro registrado, dessa forma teremos uma listagem de acordo com a filtragem solicitada</ol>
     * é usado o IgnoreCase
     * @param titulo String no nome do livro
     * @param autor String do nome do autor
     * @return List
     */
    public List<Livro> filtrarLivros(String titulo, String autor){
        List<Livro> livrosFiltrados = new ArrayList<>();
        //verifica se livro ou autor foi passado como nulo, assim não será feito a filtragem por esses parâmetros
        boolean tituloNull = true;
        boolean autorNull = true;
        if(titulo!=null){
            tituloNull = false;
        }
        if (autor!=null){
            autorNull = false;
        }
        for(Livro livroAux : livros){
            if(!tituloNull && livroAux.getTitulo().equalsIgnoreCase(titulo)){
                livrosFiltrados.add(livroAux);
            }else{
                if(!autorNull && livroAux.getAutor().getNome().equalsIgnoreCase(autor)) {
                    livrosFiltrados.add(livroAux);
                }
            }
        }
        return livrosFiltrados;
    }


    /**
     * Metodo lista os Livros com disponibilidade para realizar emprestimo
     * @return List
     */
    public List<Livro> livrosDisponiveis(){
       List<Livro> livrosDisponiveis = new ArrayList<>();
        for(Livro livroAux: livros){
            if(livroAux.getDisponibilidade()){
                livrosDisponiveis.add(livroAux);
            }
        }
        return livrosDisponiveis;
    }

    /**Metodo que registra o emprestimo do livro ao cliente
     *  <ol>Emprestimo concluido = true</ol>
     *  <ol>Emprestimo negado = false</ol>
     * @param livro livro que será feito o emprestimo
     * @param cliente responsável pelo emprestimo, quem está recebendo o livro
     * @return boolean
     */
    public boolean realizarEmprestimo(Livro livro, Cliente cliente){
        if(livro.getDisponibilidade()){
            Emprestimo emprestimo = new Emprestimo(livro, LocalDate.now(),cliente);
            emprestimos.add(emprestimo);
            cliente.getEmprestimosCliente().add(emprestimo);
            return  true;
        }
        return false;
    }

    /**Metodo que cadastra o livro na livraria e no registro do autor
     *
     * @param titulo titulo do Livro para cadastrar
     * @param autor autor do livro
     * @param genero genero do livro, selecionado pelo enum
     */
    public void cadastrarLivro(String titulo, Autor autor, Genero genero){
        Livro livro = new Livro(titulo,autor,LocalDate.now(),genero);
        System.out.println(livro.toString());
        autor.getLivrosAutoria().add(livro);
        livros.add(livro);
    }



    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}

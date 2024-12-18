import com.sistemalivraria.models.*;
import com.sistemalivraria.models.enums.Genero;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //instanciação da Livraria
        Livraria livraria1 = new Livraria();

        //instanciaçao autor
        Autor autor1 = new Autor( LocalDate.of(1839,06,21),"Machado de Assis");
        Autor autor2 = new Autor( LocalDate.of(1903, 6, 25), "George Orwell");
        livraria1.getAutores().add(autor1);
        livraria1.getAutores().add(autor2);


        //instanciaçao livros
        Livro livro1 = new Livro("Dom Casmurro",autor1,LocalDate.now(), Genero.ROMANCE);
        Livro livro2 = new Livro("Memórias Póstumas de Brás Cubas", autor1, LocalDate.now(), Genero.ROMANCE);
        Livro livro3 = new Livro("Quincas Borba", autor1, LocalDate.now(), Genero.ROMANCE);
        Livro livro4 = new Livro("1984", autor2, LocalDate.of(1949, 6, 8), Genero.ROMANCE);
        Livro livro5 = new Livro("A Revolução dos Bichos", autor2, LocalDate.of(1945, 8, 17), Genero.FANTASIA);
        Livro livro6 = new Livro("Lutando na Espanha", autor2, LocalDate.of(1938, 4, 1), Genero.BIOGRAFIA);
        livraria1.getLivros().add(livro1);
        livraria1.getLivros().add(livro2);
        livraria1.getLivros().add(livro3);
        livraria1.getLivros().add(livro4);
        livraria1.getLivros().add(livro5);
        livraria1.getLivros().add(livro6);

        //instanciação cliente
        Cliente cliente1 = new Cliente("Ana Clara", "123456789", 6265255L);
        Cliente cliente2 = new Cliente("Pedro", "123456789", 6666666L);
        livraria1.getClientes().add(cliente1);
        livraria1.getClientes().add(cliente2);

        //Emprestimo de livro
        boolean ok =  livraria1.realizarEmprestimo(livro1,cliente1);


        //menu de opções
        System.out.println("------------MENU-------------");
        System.out.println("[1] Emprestimo de Livro");
        System.out.println("[2] Devolução Livro");
        System.out.println("[3] Cadastro Cliente");
        System.out.println("[4] Cadastro Autor");
        System.out.println("[5] Cadastro Livro");
        System.out.print("Digite o numero da opção desejada: ");
        int op = sc.nextInt();
        sc.nextLine();
        boolean opValida = true;
        while(opValida){

            switch (op){
                case 1:
                    //esta variável permitira o usuário a consultar um novo livro, caso o titulo não seja encontrado (venha null)
                    int opNovamente=1;
                    while(opNovamente == 1) {
                        System.out.println("----------Pagina Emprestimo----------");
                        System.out.print("Informe o NOME do Livro: ");
                        String nomeLivro = sc.nextLine();
                        System.out.print(("Informe o nome do AUTOR do livro: "));
                        String nomeAutor = sc.nextLine();
                        System.out.print("Informe o nome do CLIENTE:");
                        String nomeCliente = sc.nextLine();
                        System.out.print("Informe o CPF do cliente: ");
                        String cpf = sc.nextLine();

                        Livro livroEmprestimo = livraria1.selecionarLivro(nomeLivro, nomeAutor);
                        Cliente cliente = livraria1.selecionarCliente(nomeCliente, cpf);

                        if (livroEmprestimo != null) {
                            if (livraria1.realizarEmprestimo(livroEmprestimo, cliente)) {
                                System.out.printf("Emprestimo do livro %s foi concluída", livroEmprestimo.getTitulo());
                                opNovamente =0;
                                opValida = false;
                                break;
                            } else {
                                System.out.println("Emprestimo do Livro foi negado, pois está indisponivel");
                            }
                        } else {
                            System.out.println("Livro não encontrado!");
                        }
                        System.out.println("Deseja tentar novamente ?");
                        System.out.println(("[1] Sim \n[2] Não"));
                        opNovamente = sc.nextInt();
                        sc.nextLine();
                    }
                    opValida = false;
                    break;
                case 2:
                    System.out.println("----------Pagina  de Devolução----------");
                    System.out.print("Informe o nome do CLIENTE:");
                    String nomeCliente = sc.nextLine();
                    System.out.print("Informe o CPF do cliente: ");
                    String cpf = sc.nextLine();

                    //selecionar cliente e iniciar lista para livros que ele pode devolver
                    Cliente cliente = livraria1.selecionarCliente(nomeCliente, cpf);
                    List<Emprestimo> emprestimosDevolver = new ArrayList<>();

                    if(cliente != null){
                        if(cliente.getEmprestimosCliente().isEmpty()) {
                            System.out.println("Cliente não tem livros para devolver! \n\n");
                            opValida = false;
                            break;
                        }

                        System.out.println("Livros não devolvidos: ");
                        for (Emprestimo emprestimoAux : cliente.getEmprestimosCliente()){
                            //caso o cliente não tenha devolvido o livro (indicado por devolvido = false) esse livro entrará na lista
                            int id = 1;
                            if(!emprestimoAux.getDevolvido()){
                                emprestimosDevolver.add(emprestimoAux);
                                System.out.printf("[%d] %s\n",id, emprestimoAux.getLivro().getTitulo());
                                id++;
                            }
                        }

                        System.out.print("Informe o numero no Livro que deseja devolver: ");
                        int opLivro = 0;
                        //validando se o número do livro escolhido está listada
                        while(true){
                            opLivro = sc.nextInt();
                            sc.nextLine();
                            if(opLivro<=emprestimosDevolver.size()+1){
                                break;
                            }else{
                                System.out.println("Digite novamente");
                            }
                        }

                        //opLivro-- pois o index começa por 0
                        opLivro--;
                        Emprestimo livroEscolhido = emprestimosDevolver.get(opLivro);
                        livroEscolhido.realizarDevolucao();
                        System.out.printf("Livro %s Devolvido com sucesso!\n\n", livroEscolhido.getLivro().getTitulo());

                    }else{
                        System.out.println("Cliente não cadastrado!");
                    }


                    opValida = false;
                    break;
                case 3:
                    System.out.println("----------Pagina  de Cadatro Cliente----------");
                    System.out.print("Informe o nome do CLIENTE:");
                    String clienteNome = sc.nextLine();
                    System.out.print("Informe o CPF do cliente: ");
                    String clienteCpf = sc.nextLine();
                    System.out.print("Informe o Contato do cliente: ");
                    String clienteContatoS = sc.nextLine();

                    //transformar clienteContatoS em Long
                    Cliente clienteCadastro = new Cliente(clienteNome,clienteCpf,Long.parseLong(clienteContatoS));
                    livraria1.getClientes().add(clienteCadastro);
                    System.out.print(clienteCadastro.toString());

                    opValida = false;
                    break;
                case 4:
                    System.out.println("----------Pagina  de Cadatro Autor----------");
                    System.out.print("Informe o nome do AUTOR:");
                    String autorNome = sc.nextLine();
                    System.out.print("Informe a DATA DE NASCIMENTO do autor: ");
                    String autorNascimento = sc.nextLine();

                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    Autor autorCadastro = new Autor(LocalDate.parse(autorNascimento,formato),autorNome);
                    livraria1.getAutores().add(autorCadastro);
                    System.out.print(autorCadastro.toString());

                    opValida = false;
                    break;
                case 5:
                    System.out.println("----------Pagina  de Cadatro Livro----------");
                    System.out.print("Informe o nome do LIVRO:");
                    String livroNome = sc.nextLine();
                    System.out.print(("Informe o nome do AUTOR do livro: "));
                    String nomeAutor = sc.nextLine();
                    System.out.print(("Informe o GENERO do Livro: "));
                    String generoaux = sc.nextLine().toLowerCase();

                    Autor livroAutor = livraria1.selecionarAutor(nomeAutor);
                    Genero livroGenero = Genero.fromString(generoaux);

                    if(livroAutor != null){
                        livraria1.cadastrarLivro(livroNome,livroAutor,livroGenero);
                        System.out.println("Livro Cadastrado com SUCESSO! \n");

                    }else{
                        System.err.println("Autor não cadastrado!!");
                    }
                    opValida = false;

                    break;
                default:
                    System.err.println("---Digite uma opção valida!!---\n");
                    System.out.println();
                    System.out.print("Digite o numero da opção desejada: ");
                    op = sc.nextInt();
                    sc.nextLine();
            }
        }
        System.out.println("\nFinalizando o Menu!\n");
     sc.close();
    }
}
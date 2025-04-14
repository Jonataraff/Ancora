package br.com.fiapAncora.run;

import br.com.fiapAncora.DAO.ClienteDAO;
import br.com.fiapAncora.DAO.MecanicoDAO;
import br.com.fiapAncora.DAO.VeiculoDAO;
import br.com.fiapAncora.model.Cliente;
import br.com.fiapAncora.model.Mecanico;
import br.com.fiapAncora.model.Peca;
import br.com.fiapAncora.model.Veiculo;
import br.com.fiapAncora.service.Validador;
import br.com.fiapAncora.DAO.PecaDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteDAO clienteDAO = new ClienteDAO();
        MecanicoDAO mecanicoDAO = new MecanicoDAO();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        Cliente clienteLogado = null;
        int opcao;

        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Login Cliente");
            System.out.println("4. Cadastrar Mecânico");
            System.out.println("5. Login Mecânico");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                	System.out.println("\n--- Cadastro de Cliente ---");
                    String nome;
                    do {
                        System.out.print("Nome: ");
                        nome = scanner.nextLine();
                    } while (!Validador.validarNome(nome));

                    String email;
                    do {
                        System.out.print("Email: ");
                        email = scanner.nextLine(); // Initialize email here
                    } while (!Validador.validarEmail(email));

                    String telefone;
                    do {
                        System.out.print("Telefone: ");
                        telefone = scanner.nextLine();
                    } while (!Validador.validarTelefone(telefone));

                    String cpf;
                    do {
                        System.out.print("CPF: ");
                        cpf = scanner.nextLine();
                    } while (!Validador.validarCpf(cpf));

                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();

                    Cliente novoCliente = new Cliente(nome, email, telefone, cpf, senha);
                    clienteDAO.inserir(novoCliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n--- Listar Clientes ---");
                    clienteDAO.listar().forEach(cliente ->
                        System.out.println("Nome: " + cliente.getNome() + ", Email: " + cliente.getEmail())
                    );
                    break;

                case 3:
                    System.out.println("\n--- Login ---");
                    System.out.print("Email: ");
                    String loginEmail = scanner.nextLine();
                    System.out.print("Senha: ");
                    String loginSenha = scanner.nextLine();

                    clienteLogado = clienteDAO.autenticar(loginEmail, loginSenha);

                    if (clienteLogado != null) {
                        System.out.println("Login realizado com sucesso! Bem-vindo, " + clienteLogado.getNome() + "!");
                        int clienteOpcao;
                        do {
                            System.out.println("\n--- MENU CLIENTE ---");
                            System.out.println("1. Cadastrar Veículo");
                            System.out.println("2. Solicitar Peça para Reparo");
                            System.out.println("0. Logout");
                            System.out.print("Escolha uma opção: ");
                            clienteOpcao = scanner.nextInt();
                            scanner.nextLine(); // Limpa o buffer

                            switch (clienteOpcao) {
                                case 1:
                                	 System.out.println("\n--- Cadastro de Veículo ---");
                                	    System.out.print("Modelo: ");
                                	    String modelo = scanner.nextLine();
                                	    System.out.print("Marca: ");
                                	    String marca = scanner.nextLine();
                                	    System.out.print("Placa: ");
                                	    String placa = scanner.nextLine();
                                	    System.out.print("Ano: ");
                                	    int ano = scanner.nextInt();
                                	    scanner.nextLine(); // Clear the buffer

                                	    // Create the Veiculo object
                                	    Veiculo novoVeiculo = new Veiculo(modelo, marca, placa, ano, clienteLogado.getId());

                                	    // Use veiculoDAO to insert the vehicle into the database
                                	    veiculoDAO.inserir(novoVeiculo);
                                	    System.out.println("Veículo cadastrado com sucesso!");
                                	    break;
                                case 2:
                                	System.out.println("\n--- Solicitação de Peça para Reparo ---");
                                    System.out.print("Nome da Peça: ");
                                    String nomePeca = scanner.nextLine();
                                    System.out.print("Preço estimado: ");
                                    double precoPeca = scanner.nextDouble();
                                    scanner.nextLine(); // Limpa o buffer

                                    Peca novaPeca = new Peca(nomePeca, precoPeca, clienteLogado.getId());

                                    // Save the part request to the database
                                    PecaDAO pecaDAO = new PecaDAO();
                                    pecaDAO.inserir(novaPeca);

                                    System.out.println("Solicitação de peça registrada com sucesso!");
                                    break;

                                case 0:
                                    System.out.println("Logout realizado com sucesso!");
                                    break;

                                default:
                                    System.out.println("Opção inválida!");
                            }
                        } while (clienteOpcao != 0);
                    } else {
                        System.out.println("Email ou senha inválidos. Tente novamente.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- Cadastro de Mecânico ---");
                    System.out.print("Nome: ");
                    String nomeMecanico = scanner.nextLine();
                    System.out.print("Email: ");
                    String emailMecanico = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefoneMecanico = scanner.nextLine();
                    System.out.print("Especialidade: ");
                    String especialidade = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senhaMecanico = scanner.nextLine();

                    Mecanico novoMecanico = new Mecanico(nomeMecanico, emailMecanico, telefoneMecanico, especialidade, senhaMecanico);
                    mecanicoDAO.inserir(novoMecanico);
                    System.out.println("Mecânico cadastrado com sucesso!");
                    break;

                case 5:
                    System.out.println("\n--- Login Mecânico ---");
                    System.out.print("Email: ");
                    String loginEmailMecanico = scanner.nextLine();
                    System.out.print("Senha: ");
                    String loginSenhaMecanico = scanner.nextLine();

                    Mecanico mecanicoLogado = mecanicoDAO.autenticar(loginEmailMecanico, loginSenhaMecanico);

                    if (mecanicoLogado != null) {
                        System.out.println("Login realizado com sucesso! Bem-vindo, " + mecanicoLogado.getNome() + "!");
                    } else {
                        System.out.println("Email ou senha inválidos. Tente novamente.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
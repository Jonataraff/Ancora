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

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteDAO clienteDAO = new ClienteDAO();
        MecanicoDAO mecanicoDAO = new MecanicoDAO();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        Cliente clienteLogado = null;
        int opcao = -1; // Initialize with an invalid value
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Login Cliente");
            System.out.println("3. Cadastrar Mecânico");
            System.out.println("4. Login Mecânico");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Por favor, insira um número inteiro.");
                scanner.nextLine(); // Clear the invalid input
                opcao = -1; // Reset to an invalid value
            }

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

                                    Veiculo novoVeiculo = new Veiculo(modelo, marca, placa, ano, clienteLogado.getId());

                                    // Use veiculoDAO to insert the vehicle into the database
                                    veiculoDAO.inserir(novoVeiculo);
                                    System.out.println("Veículo cadastrado com sucesso!");
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
                                

                case 3:
                    System.out.println("\n--- Cadastro de Mecânico ---");

                    String nomeMecanico;
                    do {
                        System.out.print("Nome: ");
                        nomeMecanico = scanner.nextLine();
                    } while (!Validador.validarNome(nomeMecanico));

                    String emailMecanico;
                    do {
                        System.out.print("Email: ");
                        emailMecanico = scanner.nextLine();
                    } while (!Validador.validarEmail(emailMecanico));

                    String telefoneMecanico;
                    do {
                        System.out.print("Telefone: ");
                        telefoneMecanico = scanner.nextLine();
                    } while (!Validador.validarTelefone(telefoneMecanico));

                    String especialidade;
                    do {
                        System.out.print("Especialidade: ");
                        especialidade = scanner.nextLine();
                    } while (!Validador.validarEspecialidade(especialidade));

                    String senhaMecanico;
                    do {
                        System.out.print("Senha: ");
                        senhaMecanico = scanner.nextLine();
                    } while (!Validador.validarSenha(senhaMecanico));

                    Mecanico novoMecanico = new Mecanico(nomeMecanico, emailMecanico, telefoneMecanico, especialidade, senhaMecanico);
                    mecanicoDAO.inserir(novoMecanico);
                    System.out.println("Mecânico cadastrado com sucesso!");
                    break;

                case 4:
                    System.out.println("\n--- Login Mecânico ---");
                    System.out.print("Email: ");
                    String loginEmailMecanico = scanner.nextLine();
                    System.out.print("Senha: ");
                    String loginSenhaMecanico = scanner.nextLine();

                    Mecanico mecanicoLogado = mecanicoDAO.autenticar(loginEmailMecanico, loginSenhaMecanico);

                    if (mecanicoLogado != null) {
                        System.out.println("Login realizado com sucesso! Bem-vindo, " + mecanicoLogado.getNome() + "!");
                        int mecanicoOpcao;
                        do {
                        	System.out.println("\n--- MENU MECÂNICO ---");
                        	System.out.println("1. Cadastrar Peça de Reposição");
                        	System.out.println("2. Listar Veículos que Precisam de Manutenção");
                        	System.out.println("0. Logout");
                        	System.out.print("Escolha uma opção: ");
                            mecanicoOpcao = scanner.nextInt();
                            scanner.nextLine(); // Limpa o buffer

                            switch (mecanicoOpcao) {
                            case 1:
                                System.out.println("\n--- Cadastro de Peça de Reposição ---");
                                System.out.print("Nome da Peça: ");
                                String nomePeca = scanner.nextLine();
                                System.out.print("Fabricante: ");
                                String fabricantePeca = scanner.nextLine();

                                double precoPeca = 0;
                                boolean precoValido = false;
                                while (!precoValido) {
                                    System.out.print("Preço: ");
                                    try {
                                        String precoInput = scanner.nextLine().replace("R$", "").trim(); // Remove "R$" se presente
                                        precoPeca = Double.parseDouble(precoInput.replace(",", ".")); // Substitui vírgula por ponto
                                        precoValido = true; // Atualiza para true se o preço for válido
                                    } catch (NumberFormatException e) {
                                        System.out.println("Preço inválido. Por favor, insira um valor numérico válido.");
                                    }
                                }

                                Peca novaPeca = new Peca(nomePeca, fabricantePeca, precoPeca);
                                PecaDAO pecaDAO = new PecaDAO();
                                pecaDAO.inserir(novaPeca);
                                System.out.println("Peça cadastrada com sucesso!");
                                break;

                                case 2:
                                	System.out.println("\n--- Veículos e seus Proprietários ---");
                                    veiculoDAO.listarVeiculosComProprietarios().forEach(System.out::println);
                                    break;

                                case 0:
                                    System.out.println("Logout realizado com sucesso!");
                                    break;

                                default:
                                    System.out.println("Opção inválida!");
                            }
                        } while (mecanicoOpcao != 0);
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
package br.com.fiapAncora.service;

public class Validador {

    public static boolean validarNome(String nome) {
        if (nome == null || nome.length() < 3) {
            System.out.println("Nome inválido.");
            return false;
        }
        return true;
    }

    public static boolean validarEmail(String email) {
        if (email == null || !email.contains("@")) {
            System.out.println("Email inválido.");
            return false;
        }
        return true;
    }

    public static boolean validarTelefone(String telefone) {
        if (telefone == null || telefone.length() < 8) {
            System.out.println("Telefone inválido.");
            return false;
        }
        return true;
    }

    public static boolean validarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            System.out.println("CPF inválido. Deve conter 11 dígitos.");
            return false;
        }
        return true;
    }
}
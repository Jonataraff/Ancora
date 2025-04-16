package br.com.fiapancora.service;

public class Validador {

	   public static boolean validarNome(String nome) {
	        if (nome == null || nome.length() < 3) {
	            System.out.println("Nome inválido. Deve conter pelo menos 3 caracteres.");
	            return false;
	        }
	        return true;
	    }

	    public static boolean validarEmail(String email) {
	        if (email == null || !email.contains("@")) {
	            System.out.println("Email inválido. Deve conter '@'.");
	            return false;
	        }
	        return true;
	    }

	    public static boolean validarTelefone(String telefone) {
	        if (telefone == null || !telefone.matches("\\d{8,}")) {
	            System.out.println("Telefone inválido. Deve conter apenas números e pelo menos 8 dígitos.");
	            return false;
	        }
	        return true;
	    }

	    public static boolean validarEspecialidade(String especialidade) {
	        if (especialidade == null || especialidade.length() < 3) {
	            System.out.println("Especialidade inválida. Deve conter pelo menos 3 caracteres.");
	            return false;
	        }
	        return true;
	    }

	    public static boolean validarSenha(String senha) {
	        if (senha == null || senha.length() < 6 || senha.contains(" ")) {
	            System.out.println("Senha inválida. Deve conter pelo menos 6 caracteres e não pode conter espaços.");
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
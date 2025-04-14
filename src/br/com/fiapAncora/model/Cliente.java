package br.com.fiapAncora.model;

public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String senha;

    public Cliente(String nome, String email, String telefone, String cpf, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.senha = senha;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public int getId() {
        return id;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
}
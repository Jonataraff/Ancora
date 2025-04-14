package br.com.fiapAncora.model;

public class Mecanico {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String especialidade;
    private String senha;

    public Mecanico(String nome, String email, String telefone, String especialidade, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.especialidade = especialidade;
        this.senha = senha;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getSenha() {
        return senha;
    }
}
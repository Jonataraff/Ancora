package br.com.fiapAncora.model;

public class Peca {
    private int id;
    private String nome;
    private double preco;
    private int mecanicoId;

    public Peca(String nome, double preco, int mecanicoId) {
        this.nome = nome;
        this.preco = preco;
        this.mecanicoId = mecanicoId;
    }

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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getMecanicoId() {
		return mecanicoId;
	}

	public void setMecanicoId(int mecanicoId) {
		this.mecanicoId = mecanicoId;
	}

    // Getters e setters
}
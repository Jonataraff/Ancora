package br.com.fiapAncora.model;

public class Veiculo {
    private int id;
    private String modelo;
    private String marca;
    private String placa;
    private int ano; // New field
    private int clienteId; // Foreign key to associate with a client

    public Veiculo(String modelo, String marca, String placa, int ano, int clienteId) {
        this.modelo = modelo;
        this.marca = marca;
        this.placa = placa;
        this.ano = ano; // Initialize the new field
        this.clienteId = clienteId;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }
    
    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

}
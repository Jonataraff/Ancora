package br.com.fiapAncora.DAO;
import br.com.fiapAncora.model.Peca;
import br.com.fiapAncora.model.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PecaDAO {

    // Method to insert a new part into the database
	public void inserir(Peca peca) {
	    String sql = "INSERT INTO pecas (nome, fabricante, preco) VALUES (?, ?, ?)";

	    try (Connection conn = Conexao.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, peca.getNome());
	        stmt.setString(2, peca.getFabricante());
	        stmt.setDouble(3, peca.getPreco());

	        stmt.executeUpdate();
	        System.out.println("Peça inserida com sucesso: " + peca.getNome());
	    } catch (SQLException e) {
	        System.err.println("Erro ao inserir peça: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


    // Method to list all parts associated with a specific mechanic ID
	public List<Veiculo> listarVeiculosComManutencao() {
	    List<Veiculo> veiculos = new ArrayList<>();
	    String sql = "SELECT * FROM veiculos WHERE precisa_manutencao = 1";

	    try (Connection conn = Conexao.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Veiculo veiculo = new Veiculo(
	                    rs.getString("modelo"),
	                    rs.getString("marca"),
	                    rs.getString("placa"),
	                    rs.getInt("ano"),
	                    rs.getInt("cliente_id")
	            );
	            veiculo.setId(rs.getInt("id"));
	            veiculos.add(veiculo);
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao listar veículos: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return veiculos;
	}
 }
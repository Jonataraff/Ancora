package br.com.fiapancora.dao;
import br.com.fiapancora.model.Peca;
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
	
	public List<Peca> listarTodas() {
        List<Peca> pecas = new ArrayList<>();
        String sql = "SELECT * FROM pecas";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Peca peca = new Peca(
                        rs.getString("nome"),
                        rs.getString("fabricante"),
                        rs.getDouble("preco")
                );
                peca.setId(rs.getInt("id"));
                pecas.add(peca);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar peças: " + e.getMessage());
            e.printStackTrace();
        }

        return pecas;
    }
	
	public void removerPorId(int id) {
        String sql = "DELETE FROM pecas WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Peça removida com sucesso!");
            } else {
                System.out.println("Nenhuma peça encontrada com o ID informado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover peça: " + e.getMessage());
            e.printStackTrace();
        }
	}

	public List<Peca> buscarPorNome(String nome) {
	    List<Peca> pecas = new ArrayList<>();
	    String sql = "SELECT * FROM pecas WHERE LOWER(TRANSLATE(nome, 'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕÇáéíóúàèìòùâêîôûãõç', 'AEIOUAEIOUAEIOUAOCaeiouaeiouaeiouaoc')) " +
	                 "LIKE LOWER(TRANSLATE(?, 'ÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÃÕÇáéíóúàèìòùâêîôûãõç', 'AEIOUAEIOUAEIOUAOCaeiouaeiouaeiouaoc'))";

	    try (Connection conn = Conexao.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, "%" + nome + "%");
	        

	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Peca peca = new Peca(
	                    rs.getString("nome"),
	                    rs.getString("fabricante"),
	                    rs.getDouble("preco")
	                );
	                peca.setId(rs.getInt("id"));
	                pecas.add(peca);
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao buscar peças: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return pecas;
	}
}


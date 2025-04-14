package br.com.fiapAncora.DAO;
import br.com.fiapAncora.model.Peca;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PecaDAO {

    // Method to insert a new part into the database
    public void inserir(Peca peca) {
        String sql = "INSERT INTO pecas (nome, preco, mecanico_id) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, peca.getNome());
            stmt.setDouble(2, peca.getPreco());
            stmt.setInt(3, peca.getMecanicoId());

            stmt.executeUpdate();
            System.out.println("Peça inserida com sucesso: " + peca.getNome());
        } catch (SQLException e) {
            System.err.println("Erro ao inserir peça: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to list all parts associated with a specific mechanic ID
    public List<Peca> listarPorMecanico(int mecanicoId) {
        List<Peca> pecas = new ArrayList<>();
        String sql = "SELECT * FROM pecas WHERE mecanico_id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mecanicoId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Peca peca = new Peca(
                            rs.getString("nome"),
                            rs.getDouble("preco"),
                            rs.getInt("mecanico_id")
                    );
                    peca.setId(rs.getInt("id"));
                    pecas.add(peca);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar peças: " + e.getMessage());
            e.printStackTrace();
        }

        return pecas;
    }
}
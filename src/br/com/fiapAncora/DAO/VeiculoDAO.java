package br.com.fiapAncora.DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.fiapAncora.model.Veiculo;

public class VeiculoDAO {

    public void inserir(Veiculo veiculo) {
    	String sql = "INSERT INTO veiculos (modelo, marca, placa, ano, cliente_id) VALUES (?, ?, ?, ?, ?)";

    	try (Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

               stmt.setString(1, veiculo.getModelo());
               stmt.setString(2, veiculo.getMarca());
               stmt.setString(3, veiculo.getPlaca());
               stmt.setInt(4, veiculo.getAno()); // Set the year
               stmt.setInt(5, veiculo.getClienteId());

               stmt.executeUpdate();
               System.out.println("Veículo inserido com sucesso: " + veiculo.getModelo());
           } catch (SQLException e) {
               System.err.println("Erro ao inserir veículo: " + e.getMessage());
               e.printStackTrace();
           }
    }

    public List<Veiculo> listarPorCliente(int clienteId) {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculos WHERE cliente_id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Veiculo veiculo = new Veiculo(
                            rs.getString("modelo"),
                            rs.getString("marca"),
                            rs.getString("placa"),
                            rs.getInt("ano"), // Include the year
                            rs.getInt("cliente_id")
                    );
                    veiculo.setId(rs.getInt("id"));
                    veiculos.add(veiculo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return veiculos;
    }
}
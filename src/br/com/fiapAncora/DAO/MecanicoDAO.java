package br.com.fiapancora.dao;
import br.com.fiapancora.model.Mecanico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MecanicoDAO {
	 private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
	    private static final String USER = "rm552939";
	    private static final String PASSWORD = "201104"; // Substitua pela sua senha

    public void inserir(Mecanico mecanico) {
        String sql = "INSERT INTO mecanicos (nome, email, telefone, especialidade, senha) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, mecanico.getNome());
            stmt.setString(2, mecanico.getEmail());
            stmt.setString(3, mecanico.getTelefone());
            stmt.setString(4, mecanico.getEspecialidade());
            stmt.setString(5, mecanico.getSenha());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Mecanico> listar() {
        List<Mecanico> mecanicos = new ArrayList<>();
        String sql = "SELECT * FROM mecanicos";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Mecanico mecanico = new Mecanico(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("especialidade"),
                        rs.getString("senha")
                );
                mecanico.setId(rs.getInt("id"));
                mecanicos.add(mecanico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mecanicos;
    }

    public Mecanico autenticar(String email, String senha) {
        String sql = "SELECT * FROM mecanicos WHERE email = ? AND senha = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Mecanico mecanico = new Mecanico(
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("telefone"),
                            rs.getString("especialidade"),
                            rs.getString("senha")
                    );
                    mecanico.setId(rs.getInt("id"));
                    return mecanico;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
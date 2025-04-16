package br.com.fiapancora.run;
import br.com.fiapancora.model.Cliente;
import br.com.fiapancora.model.Mecanico;
import br.com.fiapancora.model.Veiculo;
import br.com.fiapancora.model.Peca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.fiapancora.dao.Conexao;

public class Teste {
    public static void main(String[] args) {
        try (Connection connection = Conexao.conectar()) {
            System.out.println("Connected to the database!");

            // Test Cliente
            Cliente cliente = new Cliente("João Silva", "joao.silva@email.com", "11987654321", "12345678901", "senha123");
            String insertCliente = "INSERT INTO CLIENTE (nome, email, telefone, cpf, senha) VALUES (?, ?, ?, ?, ?)";
            int clienteId = 0;
            try (PreparedStatement stmtCliente = connection.prepareStatement(insertCliente, new String[] { "id" })) {
                stmtCliente.setString(1, cliente.getNome());
                stmtCliente.setString(2, cliente.getEmail());
                stmtCliente.setString(3, cliente.getTelefone());
                stmtCliente.setString(4, cliente.getCpf());
                stmtCliente.setString(5, cliente.getSenha());
                stmtCliente.executeUpdate();

                // Retrieve the generated ID
                try (ResultSet rs = stmtCliente.getGeneratedKeys()) {
                    if (rs.next()) {
                        clienteId = rs.getInt(1);
                    }
                }
                System.out.println("Cliente inserted into the database with ID: " + clienteId);
            }

            // Test Mecanico
            Mecanico mecanico = new Mecanico("Carlos Souza", "carlos.souza@email.com", "11912345678", "Especialista em Carros antigos", "senha456");
            String insertMecanico = "INSERT INTO MECANICOS (nome, email, telefone, especialidade, senha) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmtMecanico = connection.prepareStatement(insertMecanico)) {
                stmtMecanico.setString(1, mecanico.getNome());
                stmtMecanico.setString(2, mecanico.getEmail());
                stmtMecanico.setString(3, mecanico.getTelefone());
                stmtMecanico.setString(4, mecanico.getEspecialidade());
                stmtMecanico.setString(5, mecanico.getSenha());
                stmtMecanico.executeUpdate();
                System.out.println("Mecanico inserted into the database.");
            }

            // Test Veiculo
            Veiculo veiculo = new Veiculo("Civic", "Honda", "ABC1234", 2020, clienteId);
            String insertVeiculo = "INSERT INTO VEICULOS (modelo, marca, placa, ano, cliente_id) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmtVeiculo = connection.prepareStatement(insertVeiculo)) {
                stmtVeiculo.setString(1, veiculo.getModelo());
                stmtVeiculo.setString(2, veiculo.getMarca());
                stmtVeiculo.setString(3, veiculo.getPlaca());
                stmtVeiculo.setInt(4, veiculo.getAno());
                stmtVeiculo.setInt(5, veiculo.getClienteId());
                stmtVeiculo.executeUpdate();
                System.out.println("Veiculo inserted into the database.");
            }

            // Test Peca
            Peca peca = new Peca("Filtro de Óleo", "Bosch", 45.90);
            String insertPeca = "INSERT INTO PECAS (nome, fabricante, preco) VALUES (?, ?, ?)";
            try (PreparedStatement stmtPeca = connection.prepareStatement(insertPeca)) {
                stmtPeca.setString(1, peca.getNome());
                stmtPeca.setString(2, peca.getFabricante());
                stmtPeca.setDouble(3, peca.getPreco());
                stmtPeca.executeUpdate();
                System.out.println("Peca inserted into the database.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //removerInserts(); // Remove all inserts after testing
    }

    public static void removerInserts() {
        try (Connection connection = Conexao.conectar()) {
            System.out.println("Connected to the database!");

            // Delete from PECAS
            String deletePecas = "DELETE FROM PECAS";
            try (PreparedStatement stmtPecas = connection.prepareStatement(deletePecas)) {
                int rowsDeleted = stmtPecas.executeUpdate();
                System.out.println("Deleted " + rowsDeleted + " rows from PECAS.");
            }

            // Delete from VEICULOS
            String deleteVeiculos = "DELETE FROM VEICULOS";
            try (PreparedStatement stmtVeiculos = connection.prepareStatement(deleteVeiculos)) {
                int rowsDeleted = stmtVeiculos.executeUpdate();
                System.out.println("Deleted " + rowsDeleted + " rows from VEICULOS.");
            }

            // Delete from MECANICOS
            String deleteMecanicos = "DELETE FROM MECANICOS";
            try (PreparedStatement stmtMecanicos = connection.prepareStatement(deleteMecanicos)) {
                int rowsDeleted = stmtMecanicos.executeUpdate();
                System.out.println("Deleted " + rowsDeleted + " rows from MECANICOS.");
            }

            // Delete from CLIENTE
            String deleteClientes = "DELETE FROM CLIENTE";
            try (PreparedStatement stmtClientes = connection.prepareStatement(deleteClientes)) {
                int rowsDeleted = stmtClientes.executeUpdate();
                System.out.println("Deleted " + rowsDeleted + " rows from CLIENTE.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
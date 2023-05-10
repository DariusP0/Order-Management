package dao;

import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import connection.ConnectionFactory;

public class ClientDAO extends AbstractDAO<Client> {


    public List<Client> findByEmail(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("email");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:findByEmail " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

}

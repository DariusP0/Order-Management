package dao;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import connection.ConnectionFactory;

public class ProductDAO extends AbstractDAO<Product> {



    public Product insert(Product product) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "INSERT INTO Product (productID, productName, productQuantity, productPrice) VALUES (?, ?, ?, ?)";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, product.getproductID());
            statement.setString(2, product.getProductName());
            statement.setInt(3, product.getProductQuantity());
            statement.setInt(4, product.getProductPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return product;
    }

}

package com.boar.lil.Repositories;

import com.boar.lil.Mappers.MapperCreditCard;
import com.boar.lil.h2entity.CreditCard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;

@Repository
public class BaseRepository implements IBaseRepository {

    @Value("${spring.datasource.url}")
    private String URL;
    @Value("${spring.datasource.username}")
    private String USER;
    @Value("${spring.datasource.password}")
    private String PASSWORD;
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public CreditCard GetById(int id) {
        CreditCard creditCard = null;
        var sql = "SELECT TOP 1 * FROM CREDIT_CARD WHERE ID = ?";

        try (
             Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             statement.setInt(1, id);

             try (ResultSet resultSet = statement.executeQuery()) {
                 creditCard = MapperCreditCard.mapResultSetToCreditCard(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return creditCard;
    }

    @Override
    public long Insert(CreditCard creditCard) {
        var sql =
                "INSERT INTO CREDIT_CARD (Card_Number, First_Name, Last_Name, Address, Phone_Number, Expire_Date, CVV, Created_Date, Updated_Date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setLong(1, creditCard.CardNumber);
            statement.setString(2, creditCard.FirstName);
            statement.setString(3, creditCard.LastName);
            statement.setString(4, creditCard.Address);
            statement.setString(5, creditCard.PhoneNumber);
            statement.setString(6, creditCard.ExpireDate);
            statement.setInt(7, creditCard.CVV);
            statement.setString(8, LocalDate.now().toString());
            statement.setString(9, null);

            if (statement.executeUpdate() > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1); // Return the generated ID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int DeleteById(int id) {
        var sql = "DELETE FROM CREDIT_CARD WHERE ID = ?";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);
            var rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                return id;  // Return the ID if deletion was successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;  // Return -1 if deletion was not successful
    }
}
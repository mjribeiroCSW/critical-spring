package com.boar.lil.criticalspring.Mappers;

import com.boar.lil.Mappers.MapperCreditCard;
import com.boar.lil.h2entity.CreditCard;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class MapperCreditCardTests {

    @Test
    void mapResultSetToCreditCard_ValidResultSet_CorrectMapping() throws SQLException {
        // Arrange
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        Mockito.when(resultSet.next()).thenReturn(true); // Simulate a valid result set

        Mockito.when(resultSet.getLong("ID")).thenReturn(1L);
        Mockito.when(resultSet.getLong("CARD_NUMBER")).thenReturn(1234567890123456L);
        Mockito.when(resultSet.getString("FIRST_NAME")).thenReturn("John");
        Mockito.when(resultSet.getString("LAST_NAME")).thenReturn("Doe");
        Mockito.when(resultSet.getString("ADDRESS")).thenReturn("123 Main St");
        Mockito.when(resultSet.getString("PHONE_NUMBER")).thenReturn("555-1234");
        Mockito.when(resultSet.getString("EXPIRE_DATE")).thenReturn("12/25");
        Mockito.when(resultSet.getInt("CVV")).thenReturn(123);
        Mockito.when(resultSet.getString("CREATED_DATE")).thenReturn("2024-01-01");
        Mockito.when(resultSet.getString("UPDATED_DATE")).thenReturn("2024-01-02");

        // Act
        CreditCard creditCard = MapperCreditCard.mapResultSetToCreditCard(resultSet);

        // Assert
        assertNotNull(creditCard);
        assertEquals(1L, creditCard.id);
        assertEquals(1234567890123456L, creditCard.CardNumber);
        assertEquals("John", creditCard.FirstName);
        assertEquals("Doe", creditCard.LastName);
        assertEquals("123 Main St", creditCard.Address);
        assertEquals("555-1234", creditCard.PhoneNumber);
        assertEquals("12/25", creditCard.ExpireDate);
        assertEquals(123, creditCard.CVV);
        assertEquals("2024-01-01", creditCard.CreatedDate);
        assertEquals("2024-01-02", creditCard.UpdatedDate);

        Mockito.verify(resultSet, Mockito.times(1)).next();
    }

    @Test
    void mapResultSetToCreditCard_EmptyResultSet_ReturnsNull() throws SQLException {
        // Arrange
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        Mockito.when(resultSet.next()).thenReturn(false);

        // Act
        CreditCard creditCard = MapperCreditCard.mapResultSetToCreditCard(resultSet);

        // Assert
        assertNull(creditCard);

        Mockito.verify(resultSet, Mockito.times(1)).next();
    }
}

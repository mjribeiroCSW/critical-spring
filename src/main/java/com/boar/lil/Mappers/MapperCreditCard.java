package com.boar.lil.Mappers;

import com.boar.lil.h2entity.CreditCard;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperCreditCard {
    public static CreditCard mapResultSetToCreditCard(ResultSet resultSet) throws SQLException {
        var creditCard = new CreditCard();

        if (!resultSet.next()){
            return null;
        }

        creditCard.id = resultSet.getLong("ID");
        creditCard.CardNumber = resultSet.getLong("CARD_NUMBER");
        creditCard.FirstName = resultSet.getString("FIRST_NAME");
        creditCard.LastName = resultSet.getString("LAST_NAME");
        creditCard.Address = resultSet.getString("ADDRESS");
        creditCard.PhoneNumber = resultSet.getString("PHONE_NUMBER");
        creditCard.ExpireDate = resultSet.getString("EXPIRE_DATE");
        creditCard.CVV = resultSet.getInt("CVV");
        creditCard.CreatedDate = resultSet.getString("CREATED_DATE");
        creditCard.UpdatedDate = resultSet.getString("UPDATED_DATE");

        return creditCard;
    }
}
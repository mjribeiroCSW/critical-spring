package com.boar.lil.Services;

import com.boar.lil.h2entity.CreditCard;

import java.util.List;

public interface ICreditCardService {
    public List<Integer> GetAll();

    public CreditCard Get(int id);

    public long Insert(CreditCard creditCard);

    public int Update();

    public String Delete(int id);
}

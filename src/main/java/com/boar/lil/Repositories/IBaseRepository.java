package com.boar.lil.Repositories;

import com.boar.lil.h2entity.CreditCard;

public interface IBaseRepository {
    public CreditCard GetById(int id);

    public long Insert(CreditCard creditCard);
}
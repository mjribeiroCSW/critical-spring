package com.boar.lil.Repositories;

import com.boar.lil.h2entity.CreditCard;

public interface IBaseRepository {
    CreditCard GetById(int id);

    long Insert(CreditCard creditCard);

    int DeleteById(int id);
}
package com.boar.lil.Services;

import com.boar.lil.Repositories.IBaseRepository;
import com.boar.lil.h2entity.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CreditCardService implements ICreditCardService
{

    private final IBaseRepository repository;

    @Autowired
    public CreditCardService(IBaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Integer> GetAll() {
        List<Integer> lista = new ArrayList<>(Arrays.asList(20, 10, 50));
        return lista;
    }

    @Override
    public CreditCard Get(int id) {
        return repository.GetById(id);
    }

    @Override
    public long Insert(CreditCard creditCard) {
        return repository.Insert(creditCard);
    }

    @Override
    public int Update() {
        return 3;
    }

    @Override
    public String Delete(int id) {
        return "Deleted credit card with id: " + repository.DeleteById(id);
    }
}
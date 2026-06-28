package za.ac.cput.transactionentity.service;

import za.ac.cput.transactionentity.domain.Transaction;
import za.ac.cput.transactionentity.factory.TransactionFactory;
import za.ac.cput.transactionentity.repository.TransactionRepository;

import java.util.List;
/*
Transaction.java
Transaction service class
Author:Athini Ngquke(222352302)
Date: 28 March 2026
 */
public class TransactionService implements ITransactionService{
    private final TransactionRepository trans;

    public TransactionService(TransactionRepository trans) {
        this.trans = trans;
    }

    @Override
    public Transaction create(Transaction record) {
        Transaction transaction = TransactionFactory.createTransaction(record.getTransactionId(),record.getBuyerId(),record.getSellerId(),record.getListing(),record.getQuantity(),record.getPrice(),record.getTotal(),record.getPdate());
        return trans.save(transaction);
    }

    @Override
    public Transaction update(Transaction record) {
        Transaction transaction = TransactionFactory.createTransaction(record.getTransactionId(),record.getBuyerId(),record.getSellerId(),record.getListing(),record.getQuantity(),record.getPrice(),record.getTotal(),record.getPdate());
        return trans.save(transaction);
    }

    @Override
    public Transaction read(String id) {
        return trans.findById(id).orElse(null);
    }

    @Override
    public List<Transaction> getAll() {
        return trans.findAll();
    }

    @Override
    public void delete(String id) {
        trans.deleteById(id);
    }
}

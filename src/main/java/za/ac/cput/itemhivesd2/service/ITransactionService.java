package za.ac.cput.transactionentity.service;

import za.ac.cput.transactionentity.domain.Transaction;

import java.util.List;
/*
Transaction.java
Transaction service interface class
Author:Athini Ngquke(222352302)
Date: 28 March 2026
 */
public interface ITransactionService {
    Transaction create(Transaction record);
    Transaction update(Transaction record);
    Transaction read(String id);
    List<Transaction> getAll();
     void delete(String id);

}

package za.ac.cput.transactionentity.service;

import za.ac.cput.transactionentity.domain.Transaction;

import java.util.List;

public interface ITransactionService {
    Transaction create(Transaction record);
    Transaction update(Transaction record);
    Transaction read(String id);
    List<Transaction> getAll();
     void delete(String id);

}

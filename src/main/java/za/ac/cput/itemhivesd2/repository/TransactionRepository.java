package za.ac.cput.transactionentity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.transactionentity.domain.Transaction;
/*
Transaction.java
Transaction repository class
Author:Athini Ngquke(222352302)
Date: 28 March 2026
 */
public interface TransactionRepository extends JpaRepository<Transaction,String> {
}

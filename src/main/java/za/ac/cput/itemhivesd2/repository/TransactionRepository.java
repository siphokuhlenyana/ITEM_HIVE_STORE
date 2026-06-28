package za.ac.cput.transactionentity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.transactionentity.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,String> {
}

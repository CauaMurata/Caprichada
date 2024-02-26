package com.picpayCrud.picpayCrud.repositories;

import com.picpayCrud.picpayCrud.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

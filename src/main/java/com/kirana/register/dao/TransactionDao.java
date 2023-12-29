package com.kirana.register.dao;

import com.kirana.register.entity.Transaction;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, String> {

}

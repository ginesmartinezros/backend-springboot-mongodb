package com.pbenito.backend_springboot_mongodb.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbenito.backend_springboot_mongodb.model.Transaction;
import com.pbenito.backend_springboot_mongodb.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    public List<Map<String, Object>> getSalesByDay() {
        return transactionRepository.getSalesByDay();
    }

    public List<Map<String, Object>> getSalesByWeek() {
        return transactionRepository.getSalesByWeek();
    }

    public List<Map<String, Object>> getSalesByMonth() {
        return transactionRepository.getSalesByMonth();
    }

    public List<Map<String, Object>> getSalesByYear() {
        return transactionRepository.getSalesByYear();
    }
}
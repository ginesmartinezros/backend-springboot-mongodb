package com.pbenito.backend_springboot_mongodb.services;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbenito.backend_springboot_mongodb.dto.SalesByWeekDTO;
import com.pbenito.backend_springboot_mongodb.dto.TransactionDateDTO;
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

    public List<SalesByWeekDTO> getSalesByWeek() {
        try {
            List<SalesByWeekDTO> rawResults = transactionRepository.getSalesByWeek();
            rawResults.forEach(System.out::println);
            return transactionRepository.getSalesByWeek();
        } catch (Exception e) {
            // Manejo de excepciones
            System.err.println("Error al obtener ventas por semana: " + e.getMessage());
            throw new RuntimeException("No se pudieron obtener las ventas por semana", e);
        }
    }

    public List<Map<String, Object>> getSalesByMonth() {
        return transactionRepository.getSalesByMonth();
    }

    public List<Map<String, Object>> getSalesByYear() {
        return transactionRepository.getSalesByYear();
    }
    public List<String> getTransactionDates() {
        try {
            List<TransactionDateDTO> dateDTOs = transactionRepository.findTransactionDates();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // O cualquier formato que desees
            return dateDTOs.stream()
                    .map(dto -> sdf.format(dto.getOperationDate())) // Convertir Date a String
                    .collect(Collectors.toList());        
                }
        catch (Exception e) {
            throw new RuntimeException("Error al obtener las fechas de las transacciones", e);
        }
    }
}
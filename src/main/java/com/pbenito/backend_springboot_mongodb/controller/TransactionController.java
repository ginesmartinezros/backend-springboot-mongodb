package com.pbenito.backend_springboot_mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pbenito.backend_springboot_mongodb.model.Transaction;
import com.pbenito.backend_springboot_mongodb.services.TransactionService;

@RestController
@RequestMapping("/api/transactions") // Ruta base para los endpoints
public class TransactionController {

    private final TransactionService transactionService;

    // Token para validar las solicitudes
    private static final String REQUIRED_TOKEN =  System.getenv("SECRET_TOKEN_PBENITO");
    //TODO este token hay que pasarlo en el desplieuge

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Valida el token en el encabezado "Authorization"
    private void validateToken(String token) {
        if (token == null || !token.equals(REQUIRED_TOKEN)) {
            throw new SecurityException("Token inválido o no proporcionado.");
        }
    }

    // Endpoint para insertar una nueva transacción
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction createTransaction(
            @RequestBody Transaction transaction,
            @RequestHeader(value = "Authorization", required = true) String token) {
        validateToken(token); // Validación del token
        return transactionService.saveTransaction(transaction);
    }

    // Endpoint para obtener todas las transacciones
    @GetMapping
    public List<Transaction> getAllTransactions(@RequestHeader(value = "Authorization", required = true) String token) {
        validateToken(token); // Validación del token
        return transactionService.getAllTransactions();
    }
}

package com.pbenito.backend_springboot_mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Endpoint para insertar una nueva transacción
    @PostMapping("/insert-transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction createTransaction(
            @RequestHeader(value = "Authorization") String token,  // Requiere el token en el encabezado
            @RequestBody Transaction transaction) {

        // Aquí puedes validar el token o hacer cualquier lógica de autenticación si es necesario
        if (!isValidToken(token)) {
            throw new UnauthorizedException("Token no válido.");
        }

        return transactionService.saveTransaction(transaction);
    }
    @GetMapping("/get-all-transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction createTransaction(
            @RequestHeader(value = "Authorization") String token,  // Requiere el token en el encabezado
            @RequestBody Transaction transaction) {

        // Aquí puedes validar el token o hacer cualquier lógica de autenticación si es necesario
        if (!isValidToken(token)) {
            throw new UnauthorizedException("Token no válido.");
        }

        return transactionService.saveTransaction(transaction);
    }
// TODO implement get amountEuro dailiy/ weeekly/ monthly/ yearly

    private boolean isValidToken(String token) {
        // Aquí puedes agregar la lógica para validar el token, por ejemplo, decodificar un JWT
        return token != null && token.startsWith("Bearer ");
    }
}

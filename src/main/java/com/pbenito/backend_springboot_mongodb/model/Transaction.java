package com.pbenito.backend_springboot_mongodb.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("transactions") // Nombre de la colección en MongoDB
public class Transaction {

    @Id
    private String id; // ObjectId en MongoDB representado como String
    private String shop; // Nombre de la tienda
    private String shopId; // ID de la tienda
    private String operationType; // Tipo de operación
    private double amountEuro; // Monto en euros
    private String operationDateString; // Fecha de operación como String
    private Date operationDate; // Fecha de operación como Date
    private String transactionId; // ID de la transacción
    private String lastFourDigitsCreditCard; // Últimos 4 dígitos de la tarjeta

    // Constructor
    public Transaction(String id, String shop, String shopId, 
    String operationType, double amountEuro, String operationDateString, 
    Date operationDate, String transactionId, 
    String lastFourDigitsCreditCard) {
        this.id = id;
        this.shop = shop;
        this.shopId = shopId;
        this.operationType = operationType;
        this.amountEuro = amountEuro;
        this.operationDateString = operationDateString;
        this.operationDate = operationDate;
        this.transactionId = transactionId;
        this.lastFourDigitsCreditCard = lastFourDigitsCreditCard;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public double getAmountEuro() {
        return amountEuro;
    }

    public void setAmountEuro(double amountEuro) {
        this.amountEuro = amountEuro;
    }

    public String getOperationDateString() {
        return operationDateString;
    }

    public void setOperationDateString(String operationDateString) {
        this.operationDateString = operationDateString;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getLastFourDigitsCreditCard() {
        return lastFourDigitsCreditCard;
    }

    public void setLastFourDigitsCreditCard(String lastFourDigitsCreditCard) {
        this.lastFourDigitsCreditCard = lastFourDigitsCreditCard;
    }
}
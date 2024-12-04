package com.pbenito.backend_springboot_mongodb.dto;
import java.util.Date;

public class TransactionDateDTO {
    private Date operationDate;

    // Constructor
    public TransactionDateDTO(Date operationDate) {
        this.operationDate = operationDate;
    }

    // Getters y setters
    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }
}
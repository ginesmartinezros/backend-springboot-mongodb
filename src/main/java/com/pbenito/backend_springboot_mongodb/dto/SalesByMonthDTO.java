package com.pbenito.backend_springboot_mongodb.dto;

public class SalesByMonthDTO {
    
    private Integer month;
    private Integer year;
    private Double totalAmount;

    // Constructor
    public SalesByMonthDTO(Integer month, Integer year, Double totalAmount) {
        this.month = month;
        this.year = year;
        this.totalAmount = totalAmount;
    }

    // Getters and setters
    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "SalesByMonthDTO{" +
                "month=" + month +
                ", year=" + year +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
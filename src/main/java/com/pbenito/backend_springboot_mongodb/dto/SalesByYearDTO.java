package com.pbenito.backend_springboot_mongodb.dto;

public class SalesByYearDTO {
    
    private Integer year;
    private Double totalAmount;

    // Constructor
    public SalesByYearDTO(Integer year, Double totalAmount) {
        this.year = year;
        this.totalAmount = totalAmount;
    }

    // Getters and setters

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
        return "SalesByYearDTO{" +
                "year=" + year +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
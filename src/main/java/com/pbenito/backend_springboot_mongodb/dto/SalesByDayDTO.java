package com.pbenito.backend_springboot_mongodb.dto;

public class SalesByDayDTO { 
    
    private Integer day;
    private Integer year;
    private Double totalAmount;

    // Constructor
    public SalesByDayDTO(Integer day, Integer year, Double totalAmount) {
        this.day = day;
        this.year = year;
        this.totalAmount = totalAmount;
    }

    // Getters and setters
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
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
        return "SalesByDayDTO{" +
                "day=" + day +
                ", year=" + year +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
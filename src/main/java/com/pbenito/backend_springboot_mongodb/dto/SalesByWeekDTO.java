package com.pbenito.backend_springboot_mongodb.dto;

public class SalesByWeekDTO {
    
    private int week;
    private int year;
    private double totalAmount;

    // Constructor
    public SalesByWeekDTO(int week, int year, double totalAmount) {
        this.week = week;
        this.year = year;
        this.totalAmount = totalAmount;
    }

    // Getters and setters
    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
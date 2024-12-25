package com.pbenito.backend_springboot_mongodb.dto;

public class SalesByWeekDTO {
    
    private Integer week;
    private Integer year;
    private String yearAndWeek;
    private Double totalAmount;

    // Constructor
    public SalesByWeekDTO(Integer week, Integer year, String yearAndWeek,Double totalAmount) {
        this.week = week;
        this.year = year;
        this.yearAndWeek = yearAndWeek;
        this.totalAmount = totalAmount;
    }

    // Getters and setters
    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
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
        return "SalesByWeekDTO{" +
                "week=" + week +
                ", year=" + year +
                ", yearAndWeek=" + yearAndWeek +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
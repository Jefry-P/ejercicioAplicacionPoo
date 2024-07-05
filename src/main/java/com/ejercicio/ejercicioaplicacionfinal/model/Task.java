package com.ejercicio.ejercicioaplicacionfinal.model;

import java.time.LocalDate;

public class Task {
    private int id;
    private String description;
    private LocalDate taskDate;
    private double price;
    private Employee employee;
    private Category category;

    public Task(int id, String description, LocalDate taskDate, double price, Employee employee, Category category) {
        this.id = id;
        this.description = description;
        this.taskDate = taskDate;
        this.price = price;
        this.employee = employee;
        this.category = category;
    }

    public Task(String description, LocalDate taskDate, double price, Employee employee, Category category) {
        this.description = description;
        this.taskDate = taskDate;
        this.price = price;
        this.employee = employee;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(LocalDate taskDate) {
        this.taskDate = taskDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}

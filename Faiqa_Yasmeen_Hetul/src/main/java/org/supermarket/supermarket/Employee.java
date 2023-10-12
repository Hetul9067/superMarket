package org.supermarket.supermarket;

import java.time.LocalDateTime;

public class Employee {
    private String name;
    private double salary;
    private LocalDateTime lastPayTime;

    public Employee(String name, double salary, LocalDateTime lastPayTime ){
        this.name = name;
        this.salary = salary;
        this.lastPayTime = lastPayTime;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDateTime getLastPayTime() {
        return lastPayTime;
    }

    public void setLastPayTime(LocalDateTime lastPayTime) {
        this.lastPayTime = lastPayTime;
    }
}

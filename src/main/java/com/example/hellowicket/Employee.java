package com.example.hellowicket;

import com.example.hellowicket.model.Role;

import java.io.Serializable;

public class Employee implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private Supervisor supervisor;
    private Role role;

    public Employee(Integer id, String firstName, String lastName, Supervisor supervisor, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.supervisor = supervisor;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", supervisor=" + supervisor +
                ", role=" + role +
                '}';
    }
}
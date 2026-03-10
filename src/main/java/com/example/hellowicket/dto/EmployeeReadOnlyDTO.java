package com.example.hellowicket.dto;

import jakarta.validation.constraints.NotNull;

public class EmployeeReadOnlyDTO {
    @NotNull
    private Integer id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    public EmployeeReadOnlyDTO() {
    }

    public EmployeeReadOnlyDTO(Integer id, String firstName, String lastName) {

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

    @Override
    public String toString() {
        return "EmployeeReadOnlyDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

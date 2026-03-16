package com.example.hellowicket;

import com.example.hellowicket.model.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.ScriptAssert;

import java.io.Serializable;

@ScriptAssert(lang = "groovy", alias = "_this", script = "_this.role.name() == 'CEO' ? _this.supervisor == null : _this.supervisor != null")
public class Employee implements Serializable {
    private Integer id;

    @NotNull
    @Size(min = 1, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 30)
    private String lastName;

    private Supervisor supervisor;

    @NotNull
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
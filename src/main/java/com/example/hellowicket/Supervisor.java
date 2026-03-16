package com.example.hellowicket;

import java.io.Serializable;

public class Supervisor implements Serializable {
    private Integer id;
    private String fullName;

    public Supervisor(Integer id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Supervisor{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}

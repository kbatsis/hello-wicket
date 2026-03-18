package com.example.hellowicket;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.Iterator;
import java.util.List;

public class EmployeeDataProvider implements IDataProvider<Employee> {
    private final List<Employee> employees;

    public EmployeeDataProvider(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public Iterator<? extends Employee> iterator(long l, long l1) {
        long toIndex = l + l1;
        if (toIndex > employees.size()) {
            toIndex = employees.size();
        }
        return employees.subList((int) l, (int) toIndex).iterator();
    }

    @Override
    public long size() {
        return employees.size();
    }

    @Override
    public IModel<Employee> model(Employee employee) {
        return Model.of(employee);
    }

    @Override
    public void detach() {
        IDataProvider.super.detach();
    }
}

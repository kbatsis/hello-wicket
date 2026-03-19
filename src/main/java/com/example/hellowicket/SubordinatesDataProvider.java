package com.example.hellowicket;

import com.example.hellowicket.service.IEmployeeService;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Iterator;
import java.util.List;

public class SubordinatesDataProvider implements IDataProvider<Employee> {
    @SpringBean
    private IEmployeeService employeeService;

    private final List<Employee> supervisors;

    public SubordinatesDataProvider(Employee supervisor) {
        supervisors = employeeService.getSubordinates(supervisor);
    }

    @Override
    public Iterator<? extends Employee> iterator(long l, long l1) {
        long toIndex = l + l1;
        if (toIndex > supervisors.size()) {
            toIndex = supervisors.size();
        }
        return supervisors.subList((int) l, (int) toIndex).iterator();
    }

    @Override
    public long size() {
        return supervisors.size();
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

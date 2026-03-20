package com.example.hellowicket;

import com.example.hellowicket.service.IEmployeeService;
import jakarta.inject.Inject;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;

public class SubordinatesDataProvider implements IDataProvider<Employee> {
    @SpringBean
    private IEmployeeService employeeService;

    private static final int PAGE_SIZE = 10;

    private final Employee supervisor;

    public SubordinatesDataProvider(Employee supervisor) {
        this.supervisor = supervisor;
        Injector.get().inject(this);
    }

    @Override
    public Iterator<? extends Employee> iterator(long first, long count) {
        int offset = (int) (first / PAGE_SIZE);

        Sort sort = Sort.by("id").ascending();

        return employeeService.getSubordinates(supervisor, PageRequest.of(offset, PAGE_SIZE, sort)).iterator();
    }

    @Override
    public long size() {
        return employeeService.countSubordinates(supervisor);
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

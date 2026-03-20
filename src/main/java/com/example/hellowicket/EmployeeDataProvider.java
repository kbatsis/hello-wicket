package com.example.hellowicket;

import com.example.hellowicket.service.IEmployeeService;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Iterator;

public class EmployeeDataProvider implements IDataProvider<Employee> {
    private static final int PAGE_SIZE = 10;

    @SpringBean
    IEmployeeService employeeService;

    public EmployeeDataProvider() {
        Injector.get().inject(this);
    }

    @Override
    public long size() {
        return employeeService.countEmployees();
    }

    @Override
    public IModel<Employee> model(Employee employee) {
        return new LoadableDetachableModel<Employee>() {
            @Override
            protected Employee load() {
                return employeeService.getEmployeeById(employee.getId());
            }
        };
    }

    @Override
    public Iterator<? extends Employee> iterator(long first, long count) {
        int offset = (int) (first / PAGE_SIZE);

        Sort sort = Sort.by("id").ascending();

        return employeeService.getAllEmployees(PageRequest.of(offset, PAGE_SIZE, sort)).iterator();
    }

    @Override
    public void detach() {
        IDataProvider.super.detach();
    }
}

package com.example.hellowicket;

import com.example.hellowicket.service.IEmployeeService;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.domain.PageRequest;

import java.util.Iterator;
import java.util.List;

public class EmployeeDataProvider implements IDataProvider<Employee> {
    @SpringBean
    IEmployeeService employeeService;

    private final List<Employee> employees;

    public EmployeeDataProvider(int pageNumber, int pageSize) {
        Injector.get().inject(this);
        employees = employeeService.getAllEmployees(PageRequest.of(pageNumber, pageSize));
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
    public Iterator<? extends Employee> iterator(long l, long l1) {
        long toIndex = l + l1;
        if (toIndex > employees.size()) {
            toIndex = employees.size();
        }
        return employees.subList((int) l, (int) toIndex).iterator();
    }

    @Override
    public void detach() {
        IDataProvider.super.detach();
    }
}

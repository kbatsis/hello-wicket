package com.example.hellowicket;

import com.example.hellowicket.service.IEmployeeService;
import jakarta.inject.Inject;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.domain.PageRequest;

import java.util.Iterator;
import java.util.List;

public class SubordinatesDataProvider implements IDataProvider<Employee> {
    @SpringBean
    private IEmployeeService employeeService;

    private final Employee supervisor;
    private final List<Employee> supervisors;

    public SubordinatesDataProvider(Employee supervisor, int pageNumber, int pageSize) {
        this.supervisor = supervisor;
        Injector.get().inject(this);
        supervisors = getSubordinatesPaginated(pageNumber, 10);
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

    private List<Employee> getSubordinatesPaginated(int page, int size) {
        return employeeService.getSubordinates(supervisor, PageRequest.of(page, size));
    }
}

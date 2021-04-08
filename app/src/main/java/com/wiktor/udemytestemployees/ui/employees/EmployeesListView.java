package com.wiktor.udemytestemployees.ui.employees;


import com.wiktor.udemytestemployees.pojo.Employee;

import java.util.List;

public interface EmployeesListView {

    void showData(List<Employee> employees);

    void showError();
}

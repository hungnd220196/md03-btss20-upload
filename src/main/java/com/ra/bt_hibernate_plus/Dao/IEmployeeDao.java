package com.ra.bt_hibernate_plus.Dao;

import com.ra.bt_hibernate_plus.entity.Department;
import com.ra.bt_hibernate_plus.entity.Employee;

import java.util.List;

public interface IEmployeeDao {
    List<Employee> getEmployee();

    public Employee getEmployeeById(Integer id);

    public boolean insertEmployee(Employee employee);

    public boolean updateEmployee(Employee employee);

    public boolean deleteEmployee(Integer id);
}

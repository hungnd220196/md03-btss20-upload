package com.ra.bt_hibernate_plus.Dao;

import com.ra.bt_hibernate_plus.entity.Department;

import java.util.List;

public interface IDepartmentDao {
    List<Department> getDepartment();

    public Department getDepartmentById(Integer id);

    public boolean insertDepartment(Department department);

    public boolean updateDepartment(Department department);

    public boolean deleteDepartment(Integer id);
}

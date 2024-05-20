package com.ra.bt_hibernate_plus.controller;

import com.ra.bt_hibernate_plus.Dao.IDepartmentDao;
import com.ra.bt_hibernate_plus.Dao.IEmployeeDao;
import com.ra.bt_hibernate_plus.entity.Department;
import com.ra.bt_hibernate_plus.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeesController {
    @Autowired
    private IEmployeeDao iEmployeeDao;
    @Autowired
    private IDepartmentDao iDepartmentDao;

    @RequestMapping("/listEmployee")
    public String list(Model model) {
        // hiển thị danh sách
        model.addAttribute("employees", iEmployeeDao.getEmployee());
        return "listEmployee";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Model model) {
        model.addAttribute("employees", new Employee());
        model.addAttribute("departments", iDepartmentDao.getDepartment());
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@Validated @ModelAttribute("employees") Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "addEmployee";
        }
        boolean bl = iEmployeeDao.insertEmployee(employee);
        if (bl) {
            return "redirect:/listEmployee";
        } else {
            model.addAttribute("employees", iEmployeeDao.getEmployee());
            return "addEmployee";
        }
    }

}
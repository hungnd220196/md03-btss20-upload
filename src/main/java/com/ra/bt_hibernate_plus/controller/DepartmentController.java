package com.ra.bt_hibernate_plus.controller;

import com.ra.bt_hibernate_plus.Dao.IDepartmentDao;

import com.ra.bt_hibernate_plus.dto.DepartmentDto;
import com.ra.bt_hibernate_plus.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class DepartmentController {
    @Autowired
    private IDepartmentDao iDepartmentDao;

    @RequestMapping(value = {"/", "/listDepartment"})
    public String list(Model model) {
    // hiển thị danh sách
        model.addAttribute("departments", iDepartmentDao.getDepartment());
        return "list";
    }


    @GetMapping("/add")
    public String showAddDepartment(Model model) {
        model.addAttribute("department", new DepartmentDto());
        return "addDepartment";

    }


    @PostMapping("/addDepartment")
    public String addDepartment(@Validated @ModelAttribute("departments") DepartmentDto departmentDto, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("department", new DepartmentDto());
            return "addDepartment";
        }
        Department department = Department.builder()
                .id(departmentDto.getId())
                .name(departmentDto.getName())
                .numberEmployee(departmentDto.getNumberEmployee())
                .status(departmentDto.getStatus())
                .build();

        //Lấy đường dẫn tương đối từ thư mục của project đến thư mục images trong project
        String path = request.getServletContext().getRealPath("/image");
        //Khởi 1 đối tượng File theo đường dẫn tương đối sẽ lấy được đường dẫn tuyệt đối
        File file1 = new File(path);
        if(!file1.exists()) {
            file1.mkdirs();
        }

        MultipartFile imgFile = departmentDto.getImage();
        //lấy tên file cần upload lên
        String fileName = imgFile.getOriginalFilename();
        try {
            //Khởi tạo đường dẫn của file sẽ copy lên
            File destination = new File(file1.getAbsolutePath() + "/" + fileName);
            if (!destination.exists())
                FileCopyUtils.copy(imgFile.getBytes(), destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Gán lại tên file vào biến image của Entity
        department.setImage(fileName);
        //Save dữ liệu vào database
        boolean bl = iDepartmentDao.insertDepartment(department);
        if (bl) {
            return "redirect:/listDepartment";
        } else {
            model.addAttribute("department", department);
            return "addDepartment";
        }
    }

    @GetMapping("/edit")
    public String showEditDepartment(Model model, @RequestParam("id") Integer id) {
        model.addAttribute("department", iDepartmentDao.getDepartmentById(id));
        return "edit";
    }

    @GetMapping("/delete")
    public String deleteDepartment(@RequestParam("id") Integer id) {
        iDepartmentDao.deleteDepartment(id);
        return "redirect:/listDepartment";

    }


    @PostMapping("/editDepartment")
    public String editDepartment(@ModelAttribute("department") Department department, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit";
        }
        boolean bl = iDepartmentDao.updateDepartment(department);
        if (bl) {
            return "redirect:/listDepartment";
        } else {
            model.addAttribute("department", department);
            return "edit";
        }
    }
}

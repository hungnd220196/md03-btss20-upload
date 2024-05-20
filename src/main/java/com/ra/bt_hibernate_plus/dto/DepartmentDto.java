package com.ra.bt_hibernate_plus.dto;

import com.ra.bt_hibernate_plus.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentDto {
    @Column(name = "id")
    private Integer id;
    @NotEmpty(message = "Department name is required")
//    @Size(min = 2, max = 100, message = "Department name must be between 2 and 100 characters")
    private String name;
    @Min(value = 1, message = "Number of employees must be at least 1")
    private int numberEmployee;
    @Column(name = "status")
    private Boolean status;
    private List<Employee> employees;
    @Column(name = "image")
    private MultipartFile image;
}

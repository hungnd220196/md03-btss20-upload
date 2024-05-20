package com.ra.bt_hibernate_plus.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "department")

public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotEmpty(message = "Department name is required")
//    @Size(min = 2, max = 100, message = "Department name must be between 2 and 100 characters")
    private String name;
    @Min(value = 1, message = "Number of employees must be at least 1")
    private int numberEmployee;
    @Column(name = "status")
    private Boolean status;
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Employee> employees;
    @Column(name = "image")
    private String image;
}

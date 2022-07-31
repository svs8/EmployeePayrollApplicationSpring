package com.bridgelabz.employeepayrollapp.model;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name= "employee_payroll")
public @Data class EmployeePayrollData {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="employee_id")
    private int employeeId;
    @Column(name="name")
    private String name;
    private long salary;
    public String gender;
    public LocalDate startDate;
    public String note;
    public String profilePic;

    @ElementCollection
    @CollectionTable(name="employee_department",
                    joinColumns = @JoinColumn(name="id"))
    @Column(name = "department")
    public List<String> departments;

    public EmployeePayrollData() {
    }

    public EmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
        this.updateEmployeePayrollData(empPayrollDTO);
    }

    public void updateEmployeePayrollData(EmployeePayrollDTO employeePayrollDTO) {
        this.name = employeePayrollDTO.name;
        this.salary = employeePayrollDTO.salary;
        this.gender = employeePayrollDTO.gender;
        this.note = employeePayrollDTO.note;
        this.startDate = employeePayrollDTO.startDate;
        this.profilePic = employeePayrollDTO.profilePic;
        this.departments = employeePayrollDTO.departments;
    }
}

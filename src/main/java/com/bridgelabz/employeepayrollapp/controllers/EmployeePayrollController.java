package com.bridgelabz.employeepayrollapp.controllers;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import com.bridgelabz.employeepayrollapp.service.IEmployeePayrollService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
@Slf4j
public class EmployeePayrollController {

    @Autowired
    private IEmployeePayrollService employeePayrollService;

    @RequestMapping(value={"","/","/get"})
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(){
        List<EmployeePayrollData> empDataList= null;
        empDataList =  employeePayrollService.getEmployeePayrollData();
        ResponseDTO respDTO = new ResponseDTO("Get Call Success",empDataList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId")int empId){
       EmployeePayrollData employeePayrollData= employeePayrollService.getEmployeePayrollDataById(empId);
        ResponseDTO respDTO = new ResponseDTO("Get Call for ID Successful",employeePayrollData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addEmployeePayrollData(@Valid @RequestBody EmployeePayrollDTO employeePayrollDTO){
        log.debug("Employee DTO: "+employeePayrollDTO.toString());
        EmployeePayrollData employeePayrollData= employeePayrollService.createEmployeePayrollData(employeePayrollDTO);
        ResponseDTO respDTO = new ResponseDTO("Created Employee Payroll Data Successfully",employeePayrollData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    @GetMapping("/department/{department}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("department") String department){
        List<EmployeePayrollData> empDataList = null;
        empDataList = employeePayrollService.getEmployeesByDepartment(department);
        ResponseDTO responseDTO = new ResponseDTO("Get Call For ID Department Successful",empDataList);
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }
    @PutMapping("/update/{empId}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") int empId,@Valid @RequestBody EmployeePayrollDTO employeePayrollDTO){
        EmployeePayrollData employeePayrollData= employeePayrollService.updateEmployeePayrollData(empId,employeePayrollDTO);
        ResponseDTO respDTO = new ResponseDTO("Updated Employee Payroll Data Successfully",employeePayrollData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId){
        employeePayrollService.deleteEmployeePayrollData(empId);
        ResponseDTO respDTO =new ResponseDTO("Deleted Successfully"," Deleted Id: " +empId);
        return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
    }
}

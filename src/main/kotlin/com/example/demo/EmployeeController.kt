package com.example.demo

import com.example.demo.service.EmpService
import com.example.demo.entity.Employee
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/employees")
class EmployeeController(private val service: EmpService) {

    @GetMapping("/")
    fun home(): String{
        return "home"
    }

    @GetMapping()
    fun getAll(): List<Employee>{
        return service.getAllEmployees()
    }

    @GetMapping("/filtered")
    fun filterEmployees(): List<Employee> {
        return service.filteredEmps
    }

    @GetMapping("/search")
    fun searchEmployee(@RequestParam(required=false) name: String?): List<Employee> {
        var emp: List<Employee>? = null
        if(name != null){
            emp = service.searchEmployee(name)
        }else{
            emp = service.searchEmployee(keyword="Bob")
        }
        return emp
    }

    @GetMapping("/sort")
    fun sortedBy(): List<Employee> {
            var emp: List<Employee>? = null
            emp = service.sortedBySalary
            return emp
        }


}
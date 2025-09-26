package com.example.demo

import com.example.demo.HTMLdsl.mainPage
import com.example.demo.service.EmpService
import com.example.demo.entity.Employee
import com.example.demo.repository.CatFactsClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/employees")
class EmployeeController(private val service: EmpService) {

//    @GetMapping("/")
//    fun home(): String{
//        return "home"
//        val fact = CatFactsClient.getRan
//        return mainPage(fact.fact.toString()).toString()
//    }

    @GetMapping()
    fun getAll(): ResponseEntity<List<Employee>>{
        val emps : List<Employee> = service.getAllEmployees()
        return ResponseEntity.ok(emps)
    }

    @GetMapping("/filtered")
    fun filterEmployees(): ResponseEntity<List<Employee>>{
        return ResponseEntity.ok(service.filteredEmps)
    }

    @GetMapping("/search")
    fun searchEmployee(@RequestParam(required=false) name: String?): ResponseEntity<List<Employee>>{
        var emp: List<Employee>? = null
        if(name != null){
            emp = service.searchEmployee(name)
        }else{
            emp = service.searchEmployee(keyword="Bob")
        }
        return ResponseEntity.ok(emp)
    }

    @GetMapping("/sort")
    fun sortedBy(): ResponseEntity<List<Employee>>{
            var emp: List<Employee>? = null
            emp = service.sortedBySalary
            return ResponseEntity.ok(emp)
        }


}
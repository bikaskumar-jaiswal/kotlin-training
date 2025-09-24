package com.example.demo.service

import com.example.demo.entity.Employee
import com.example.demo.repository.EmployeeRepository
import org.springframework.stereotype.Service

@Service
class EmpService(private val repo: EmployeeRepository) {

    fun getAllEmployees(): List<Employee> = repo.findAll()

    fun filterEmployees(emp: List<Employee>, action: (Employee) -> Boolean): List<Employee> {
        return emp.filter{action(it)}
    }
    val filteredEmps = filterEmployees(repo.findAll()){it.salary > 6000}

    fun searchEmployee(keyword: String): List<Employee>{
        return repo.findAll().filter { it.name.contains(keyword) || it.position.contains(keyword) }
    }

    fun <T: Comparable<T>> sortEmployees(
        keySelector: (Employee) -> T
    ): List<Employee>{
        return  repo.findAll().sortedBy(keySelector)
    }
    val sortedBySalary = sortEmployees({it.salary})


}

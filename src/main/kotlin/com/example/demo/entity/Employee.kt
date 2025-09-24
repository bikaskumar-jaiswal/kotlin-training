package com.example.demo.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "employee")
data class Employee(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String,
    var position: String,
    var salary: Double
)

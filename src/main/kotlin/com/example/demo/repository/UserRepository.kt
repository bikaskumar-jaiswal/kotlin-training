package com.example.demo.repository

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


data class User(
    val id: Int,
    val email: String,
    val username: String,
    val name: String
)

@FeignClient(name="users-list", url="https://jsonplaceholder.typicode.com/")
interface UserRepository{

    @GetMapping("/users")
    fun getAllUsers(): List<User>

    @GetMapping("/users/{id}")
    fun getUser(@PathVariable id: Int): User

}
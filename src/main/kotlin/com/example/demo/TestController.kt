package com.example.demo

import com.example.demo.HTMLdsl.mainPage
import com.example.demo.repository.CatFactsClient
import com.example.demo.repository.User
import com.example.demo.repository.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class TestController(private val catFactsClient: CatFactsClient, private val usersClient: UserRepository) {

    @GetMapping("/")
    fun cats(): String {
        val fact = catFactsClient.getRandomFact()
        return mainPage(fact.fact.toString()).toString()
    }

    @GetMapping("/get-users")
    fun myUsers(): String{
        val users = usersClient.getAllUsers()
//        val usernames = users.joinToString(", ") { it.username }

        return mainPage(users).toString()
    }

    @GetMapping("/get-user/{id}")
    fun myUser(@PathVariable id: Int): String {
        val user = usersClient.getUser(id)

        return mainPage(user).toString()
    }
}
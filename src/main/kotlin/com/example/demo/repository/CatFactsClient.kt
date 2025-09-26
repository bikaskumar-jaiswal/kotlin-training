package com.example.demo.repository

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping


data class CatFact(val fact: String, val length: Int)

@FeignClient(name="cat-facts", url="https://catfact.ninja")
interface CatFactsClient{

    @GetMapping("/fact")
    fun getRandomFact(): CatFact

}
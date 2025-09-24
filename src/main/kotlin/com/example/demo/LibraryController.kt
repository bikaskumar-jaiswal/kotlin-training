package com.example.demo

import com.example.demo.entity.Books
import com.example.demo.service.BooksService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/books")
class LibraryController(private val service: BooksService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<Books>>{
        return ResponseEntity.ok(service.getAllBooks())
    }

    @GetMapping("/filtered")
    fun filterEmployees(): ResponseEntity<List<Books>> {
        return ResponseEntity.ok(service.filteredBooks)
    }

    @GetMapping("/search")
    fun searchEmployee(@RequestParam(required=false) name: String?): ResponseEntity<List<Books>> {
        var book: List<Books>? = null
        if(name != null){
            book = service.searchBook(name)
        }else{
            book = service.searchBook(keyword="Effective Java")
        }
        return ResponseEntity.ok(book)
    }

    @GetMapping("/sort")
    fun sortedBy(): ResponseEntity<List<Books>> {
        var book: List<Books>? = null
        book = service.sortedByYear
        return ResponseEntity.ok(book)
    }


}
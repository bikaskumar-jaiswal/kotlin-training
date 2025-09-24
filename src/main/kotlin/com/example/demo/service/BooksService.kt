package com.example.demo.service


import com.example.demo.entity.Books
import com.example.demo.repository.BooksRepository
import org.springframework.stereotype.Service

@Service
class BooksService(private val repo: BooksRepository) {

    fun getAllBooks(): List<Books> = repo.findAll()

    fun filterBooks(books: List<Books>, action: (Books) -> Boolean): List<Books> {
        return books.filter{action(it)}
    }
    val filteredBooks = filterBooks(repo.findAll()){it.available}

    fun searchBook(keyword: String): List<Books>{
        return repo.findAll().filter { it.title.contains(keyword) || it.author.contains(keyword) }
    }

    fun <T: Comparable<T>> sortBooks(
        keySelector: (Books) -> T
    ): List<Books>{
        return  repo.findAll().sortedBy(keySelector)
    }
    val sortedByYear = sortBooks({it.year})

}

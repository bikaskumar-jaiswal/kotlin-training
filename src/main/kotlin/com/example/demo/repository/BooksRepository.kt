package com.example.demo.repository

import com.example.demo.entity.Books
import org.springframework.data.jpa.repository.JpaRepository

interface BooksRepository : JpaRepository<Books, Long>
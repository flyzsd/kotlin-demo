package com.shudong.kotlin.demo.repository

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import java.time.Instant

// many to many relationship with multiple aggregate root
data class Author(
    @Id
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: Instant
)

@Table("book_author")
data class AuthorRef(
    val author: Long
)

data class Book(
    @Id
    val id: Long? = null,
    val name: String,
    val isbn: String,
    // in this case, the book owns references to authors
    val authorRefs: Set<AuthorRef> = setOf(),
    val price: Double,
    val publishedDate: Instant
)

interface AuthorRepository : CrudRepository<Author, Long>

interface BookRepository : CrudRepository<Book, Long>
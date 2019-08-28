package com.shudong.kotlin.demo.repository

import org.hibernate.annotations.GenericGenerator
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Version

@Entity
data class Person(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    val id: String? = null,
    val name: String,
    val age: Int,
    @Version
    val version: Long = 0
)

@Repository
interface PersonRepository: CrudRepository<Person, String>
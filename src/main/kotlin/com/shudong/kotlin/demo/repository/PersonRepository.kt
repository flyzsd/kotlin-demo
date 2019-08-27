package com.shudong.kotlin.demo.repository

import org.springframework.stereotype.Repository

data class Person(var id: Int?, val name: String, val age: Int)

@Repository
class PersonRepository {
}
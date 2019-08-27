package com.shudong.kotlin.demo.controller

import com.shudong.kotlin.demo.repository.Person
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/persons")
class PersonController {
    @GetMapping
    fun findAll(): List<Person> = listOf()
}
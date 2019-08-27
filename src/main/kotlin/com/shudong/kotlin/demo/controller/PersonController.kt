package com.shudong.kotlin.demo.controller

import com.shudong.kotlin.demo.repository.Person
import com.shudong.kotlin.demo.service.PersonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/persons")
class PersonController(private val personService : PersonService) {
    @GetMapping
    fun findAll(): List<Person> = personService.findAll()
}
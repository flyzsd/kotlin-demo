package com.shudong.kotlin.demo.controller

import com.shudong.kotlin.demo.client.GithubClient
import com.shudong.kotlin.demo.repository.Person
import com.shudong.kotlin.demo.service.PersonService
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/persons")
class PersonController(
    private val personService: PersonService,
    private val githubClient: GithubClient
) {
    @Value("\${app.cron.pattern}")
    lateinit var appCronPattern: String

    @GetMapping
    fun findAll(): List<Person> = personService.findAll().also {
        val contributors = githubClient.contributors("flyzsd", "spring-rest", "xyz-token", "123456789");
        println(contributors);
    }
}
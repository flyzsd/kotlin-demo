package com.shudong.kotlin.demo.controller

import com.shudong.kotlin.demo.client.GithubClient
import com.shudong.kotlin.demo.repository.Person
import com.shudong.kotlin.demo.service.PersonService
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/persons")
class PersonController(
    private val personService: PersonService,
    private val githubClient: GithubClient,
    private val cacheManager: CacheManager
) {
    @Value("\${app.cron.pattern}")
    lateinit var appCronPattern: String

    @GetMapping
    fun findAll(): List<Person> = personService.findAll().also {
        val contributors = githubClient.contributors("flyzsd", "spring-rest", "xyz-token", "123456789");
        println(contributors);
    }

    @GetMapping("/book")
    fun findBook(): Unit = personService.findBook()

    @GetMapping("/calculate")
    fun calculate(@RequestParam message: String): BigDecimal {
        val pi = personService.calculatePiDecimal(message)
        println(cacheManager.getCache("cache1"))
        return pi
    }
}
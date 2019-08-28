package com.shudong.kotlin.demo.service

import com.shudong.kotlin.demo.logger
import com.shudong.kotlin.demo.repository.Person
import com.shudong.kotlin.demo.repository.PersonRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PersonService(private val personRepository: PersonRepository) {

    companion object {
        private val logger = LoggerFactory.getLogger(PersonService::class.java)
    }

    fun findAll(): List<Person> {
        var person = personRepository.save(Person(name = "shudong", age = 35))
        println(person)
        logger.info("findAll");
        logger().info("findAll2")
        println(logger === logger())
        return listOf<Person>()
    }

}
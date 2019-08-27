package com.shudong.kotlin.demo.service

import com.shudong.kotlin.demo.logger
import com.shudong.kotlin.demo.repository.Person
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PersonService {

    companion object {
        private val logger = LoggerFactory.getLogger(PersonService::class.java)
    }

    fun findAll(): List<Person> {
        logger.info("findAll");
        logger().info("findAll2")
        println(logger === logger())
        return listOf<Person>()
    }

}
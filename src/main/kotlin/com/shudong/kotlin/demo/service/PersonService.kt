package com.shudong.kotlin.demo.service

import com.shudong.kotlin.demo.logger
import com.shudong.kotlin.demo.repository.*
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PersonService(
    private val personRepository: PersonRepository,
    private val orderRepository: OrderRepository,
    private val jdbcTemplate: NamedParameterJdbcTemplate
) {

    companion object {
        private val logger = LoggerFactory.getLogger(PersonService::class.java)
    }

    @Transactional
    fun findAll(): List<Person> {
        println(jdbcTemplate)
        val items = setOf(OrderItem(product = "milk", quantity = 1), OrderItem(product = "phone", quantity = 9))
        orderRepository.save(PurchaseOrder(name = "hello", age = 19, items = items))
        println(orderRepository.findAll().toList())
        orderRepository.findAll().forEach { orderRepository.updateOrder(it.id!!, it.age, it.version) }
        println(orderRepository.findAll().toList())
        val orders = orderRepository.findAllByNameAndAge("hello", 19)
        println(orders)
        var person = personRepository.save(Person(id = "1234", name = "shudong", age = 35))
        println(person)
        var updatedCount = personRepository.updatePerson(person.id!!, 36)
        println("updatedCount = $updatedCount")
        var findByIdOrNull: Person? = personRepository.findByIdOrNull(person.id!!)
        println(findByIdOrNull)
        logger.info("findAll");
        logger().info("findAll2")
        println(logger === logger())
        return personRepository.findAll().toList()
    }

}
package com.shudong.kotlin.demo.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.shudong.kotlin.demo.logger
import com.shudong.kotlin.demo.repository.*
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@Service
class PersonService(
    private val personRepository: PersonRepository,
    private val orderRepository: OrderRepository,
    private val jdbcTemplate: NamedParameterJdbcTemplate,
    private val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository
) {

    companion object {
        private val logger = LoggerFactory.getLogger(PersonService::class.java)
        private val mapper = jacksonObjectMapper().apply {
            registerModule(JavaTimeModule())
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        }
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
        val id = UUID.randomUUID().toString();
        val person = personRepository.save(Person(id = id, name = "shudong\uD83D\uDE42", age = 35, auxiliaryJson = """{
            |"name":"jeffrey"
            |}""".trimMargin()))
        println(person)
        val person2 = personRepository.findByIdOrNull(id)
        println(person2)
        println(person == person2)
        val updatedCount = personRepository.updatePerson(person.id!!, 36)
        println("updatedCount = $updatedCount")
        val findByIdOrNull: Person? = personRepository.findByIdOrNull(person.id!!)
        println(findByIdOrNull)
        logger.info("findAll");
        logger().info("findAll2")
        println(logger === logger())
        return personRepository.findAll().toList()
    }

    @Transactional
    fun findBook(): Unit {
        val author = authorRepository.save(Author(firstName = "John", lastName = "Miller", dateOfBirth = Instant.now()))
        val author2 = authorRepository.save(Author(firstName = "Will", lastName = "Smith", dateOfBirth = Instant.now()))
        val authorRefs = setOf(AuthorRef(author = author.id!!), AuthorRef(author = author2.id!!))
        val book = bookRepository.save(Book(name = "Think In Java", isbn = "ISBN1234", price = 9.9, publishedDate = Instant.now(), authorRefs = authorRefs))
        println(author)
        println(book)
        val book2 = bookRepository.save(Book(name = "Kotlin In Action", isbn = "ISBN4567", price = 19.9, publishedDate = Instant.now(), authorRefs = setOf(AuthorRef(author = author2.id!!))))
        println(mapper.writeValueAsString(book2))
        val books = bookRepository.findAll().toList()
        println(books)
        bookRepository.deleteById(book2.id!!)
    }

    @Cacheable(cacheNames = ["cache1"], key = "#i.hashCode()")
    fun calculatePiDecimal(i: String): BigDecimal {
        logger.info("calculatePiDecimal")
        return BigDecimal.ZERO
    }

}
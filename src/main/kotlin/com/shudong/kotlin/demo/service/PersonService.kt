package com.shudong.kotlin.demo.service

import com.shudong.kotlin.demo.logger
import com.shudong.kotlin.demo.repository.*
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

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
        val person = personRepository.save(Person(id = "1234", name = "shudong", age = 35))
        println(person)
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
        val author = authorRepository.save(Author(firstName = "John", lastName = "Miller", dateOfBirth = LocalDate.of(1980, 12, 12)))
        val author2 = authorRepository.save(Author(firstName = "Will", lastName = "Smith", dateOfBirth = LocalDate.of(1977, 5, 25)))
        val authorRefs = setOf(AuthorRef(author = author.id!!), AuthorRef(author = author2.id!!))
        val book = bookRepository.save(Book(name = "Think In Java", isbn = "ISBN1234", price = 9.9, publishedDate = LocalDate.of(2018, 12, 12), authorRefs = authorRefs))
        println(author)
        println(book)
        val book2 = bookRepository.save(Book(name = "Kotlin In Action", isbn = "ISBN4567", price = 19.9, publishedDate = LocalDate.of(2019, 5, 12), authorRefs = setOf(AuthorRef(author = author2.id!!))))
        println(book2)
        val books = bookRepository.findAll().toList()
        println(books)
        bookRepository.deleteById(2)
    }

}
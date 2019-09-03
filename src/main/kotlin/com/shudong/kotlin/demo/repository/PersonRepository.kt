package com.shudong.kotlin.demo.repository

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.time.Instant

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

@Table("order_table")
data class Order(
    @Id
    val id: Long? = null,
    val name: String,
    val age: Int,
    @Column("modified_on")
    val modifiedOn: Instant = Instant.now(),
    @Version
    val version: Long = 0
)

@Table("person")
data class Person(
    @Id
    val id: String? = null,
    val name: String,
    val age: Int,
    val direction: Direction = Direction.EAST,
    @Column("modified_on")
    val modifiedOn: Instant = Instant.now(),
    @Version
    val version: Long = 0
)

interface PersonRepository : CrudRepository<Person, String> {
    @Modifying
    @Query("UPDATE person set age = :age WHERE id = :id")
    fun updatePerson(@Param("id") id: String, @Param("age") age: Int): Int
}

interface OrderRepository : CrudRepository<Order, Long> {
    @Modifying
    @Query("UPDATE order_table set age = :age, version = :version + 1 WHERE id = :id AND version = :version")
    fun updateOrder(@Param("id") id: Long, @Param("age") age: Int, @Param("version") version: Long): Int

    @Query("SELECT * FROM order_table WHERE name = :name AND age = :age")
    fun findAllByNameAndAge(@Param("name") name: String, @Param("age") age: Int): List<Order>
}
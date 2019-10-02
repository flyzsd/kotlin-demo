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

@Table("purchase_order")
data class PurchaseOrder(
    @Id
    val id: Long? = null,
    val name: String,
    val age: Int,
    @Column("modified_on")
    val modifiedOn: Instant = Instant.now(),
    @Version
    val version: Long = 0,
    // one to many relationship with one aggregate root
    // @Column("purchase_order")
    val items: Set<OrderItem> = setOf()
)

data class OrderItem(
    @Id
    val id: Long? = null,
    val quantity: Int,
    val product: String
)

@Table("person")
data class Person(
    @Id
    val id: String,
    val name: String,
    val age: Int,
    val direction: Direction = Direction.EAST,
    @Column("auxiliary_json")
    val auxiliaryJson: String = "{}",
    val auxiliaryName: String? = null,          //read-only column from JSON
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

interface OrderRepository : CrudRepository<PurchaseOrder, Long> {
    @Modifying
    @Query("UPDATE purchase_order set age = :age, version = :version + 1 WHERE id = :id AND version = :version")
    fun updateOrder(@Param("id") id: Long, @Param("age") age: Int, @Param("version") version: Long): Int

    @Query("SELECT * FROM purchase_order WHERE name = :name AND age = :age")
    fun findAllByNameAndAge(@Param("name") name: String, @Param("age") age: Int): List<PurchaseOrder>
}
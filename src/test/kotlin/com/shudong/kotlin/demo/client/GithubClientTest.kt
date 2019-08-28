package com.shudong.kotlin.demo.client

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles


@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
@SpringBootTest
class GithubClientTest {
    @Autowired
    lateinit var githubClient: GithubClient

    @Test
    @DisplayName("github contributors")
    fun testGetContributors() {
        val contributors = githubClient.contributors("flyzsd", "spring-rest", "xyz-token", "123456789");
        println(contributors);
    }
}
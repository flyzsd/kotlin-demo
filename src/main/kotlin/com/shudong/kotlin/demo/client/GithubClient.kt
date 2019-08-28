package com.shudong.kotlin.demo.client

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.cloud.openfeign.FeignClientsConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

class OAuth2RequestInterceptor : RequestInterceptor {

    override fun apply(template: RequestTemplate) {
        template.header("Authorization", "ABC");
    }

}

class GithubClientConfiguration : FeignClientsConfiguration() {

    @Bean
    fun basicAuthRequestInterceptor(): RequestInterceptor = OAuth2RequestInterceptor()

}

data class Contributor(val login: String, val contributions: Int)

@FeignClient(name = "github", url = "\${feign.github.url}", configuration = [GithubClientConfiguration::class])
interface GithubClient {

    @GetMapping(value = ["/repos/{owner}/{repo}/contributors"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun contributors(@PathVariable("owner") owner: String, @PathVariable("repo") repo: String,
                     @RequestHeader("X-Auth-Token") token: String, @RequestParam(name = "queryId", required = false, defaultValue = "abc") queryId: String): List<Contributor>

}
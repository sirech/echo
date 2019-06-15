package com.hceris.echo

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class HelloControllerTest(@Autowired val webApplicationContext: WebApplicationContext) {
    val mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()

    @Test
    fun `hello returns world`() {
        mockMvc.perform(get("/hello")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json("""{ "name": "world" }"""))
                .andExpect(status().isOk)
    }

    @Test
    fun `hello returns name`() {
        mockMvc.perform(get("/hello/you")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json("""{ "name": "You" }"""))
                .andExpect(status().isOk)
    }
}

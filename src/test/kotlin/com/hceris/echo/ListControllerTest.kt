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
internal class ListControllerTest(@Autowired val webApplicationContext: WebApplicationContext) {
    val mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()

    @Test
    fun `numbers returns a list of numbers`() {
        mockMvc.perform(get("/list/numbers")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json("""{ "elements": [1, 2, 3, 4, 5] }"""))
                .andExpect(status().isOk)
    }

    @Test
    fun `numbers can customize the length of the list`() {
        mockMvc.perform(get("/list/numbers/7")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json("""{ "elements": [1, 2, 3, 4, 5, 6, 7] }"""))
                .andExpect(status().isOk)
    }
}


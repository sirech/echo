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
internal class FishControllerTest(@Autowired val webApplicationContext: WebApplicationContext) {
    val mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()

    val template = """{
                    | "es": "merluza",
                    | "en": "hake",
                    | "de": "seehecht",
                    | "fr": "merlu"
                    |}""".trimMargin()

    @Test
    fun `returns an empty hash if there are no corresponding entries`() {
        mockMvc.perform(get("/fish/gamusino")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound)
    }

    @Test
    fun `returns the entry for a full match`() {
        mockMvc.perform(get("/fish/merluza")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(template))
                .andExpect(status().isOk)
    }

    @Test
    fun `is case insensitive`() {
        mockMvc.perform(get("/fish/Merluza")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(template))
                .andExpect(status().isOk)
    }

    @Test
    fun `returns the entry for a partial match`() {
        mockMvc.perform(get("/fish/teufel")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("""{
                    | "es": "rape",
                    | "en": "monkfish",
                    | "de": "seeteufel",
                    | "fr": "râpé"
                    |}""".trimMargin()))
                .andExpect(status().isOk)
    }
}



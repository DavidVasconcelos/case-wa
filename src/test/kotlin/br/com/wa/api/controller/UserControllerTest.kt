package br.com.wa.api.controller

import br.com.wa.api.domain.service.UserService
import br.com.wa.api.exception.BusinessValidationException
import br.com.wa.api.exception.Error
import br.com.wa.api.exception.ErrorResponse
import br.com.wa.api.exception.NotFoundException
import br.com.wa.api.mock.MockTest
import br.com.wa.api.support.annotations.CustomTest
import br.com.wa.api.support.annotations.andResultBodyMatches
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@CustomTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var service: UserService

    @Autowired
    private lateinit var mockTest: MockTest

    @Test
    fun `Successful test get user`() {
        val user = mockTest.getMockUser()
        val expectedUser =
            mapper.writeValueAsString(user) as String

        every { service.getById(any()) } returns user

        mockMvc.perform(
            MockMvcRequestBuilders.get("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andResultBodyMatches(json = expectedUser)
    }

    @Test
    fun `Returns not found exception when user does not exist`() {
        val exceptionMessage = "User not found"
        val errors = arrayListOf(
            Error(
                codigo = HttpStatus.NOT_FOUND.value(),
                mensagem = exceptionMessage
            )
        )
        val expectedErrorResponse = ErrorResponse("NotFound", errors)
        val expectedErrorJSON = mapper.writeValueAsString(expectedErrorResponse) as String

        every { service.getById(any()) } throws NotFoundException(exceptionMessage)

        mockMvc.perform(
            MockMvcRequestBuilders.get("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
            MockMvcResultMatchers.status().isNotFound
        ).andResultBodyMatches(json = expectedErrorJSON)
    }

    @Test
    fun `Successful test of saving user`() {
        val user = mockTest.getMockUser()
        val userJSON = mapper.writeValueAsString(user) as String

        every { service.saveUser(any()) } returns user

        mockMvc.perform(
            MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(userJSON)
        ).andExpect(
            MockMvcResultMatchers.status().isCreated
        )
    }

    @Test
    fun `Returns business validation exception when user is invalid when trying to save it`() {
        val user = mockTest.getMockUser()
        val userJSON = mapper.writeValueAsString(user) as String
        val exceptionMessage = "Invalid user"
        val errors = arrayListOf(
            Error(
                codigo = HttpStatus.BAD_REQUEST.value(),
                mensagem = exceptionMessage
            )
        )
        val expectedErrorResponse = ErrorResponse("Validation failed", errors)
        val expectedErrorJSON = mapper.writeValueAsString(expectedErrorResponse) as String

        every { service.saveUser(any()) } throws BusinessValidationException(exceptionMessage)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(userJSON)
        ).andExpect(
            MockMvcResultMatchers.status().isBadRequest
        ).andResultBodyMatches(json = expectedErrorJSON)
    }

    @Test
    fun `User update successful test`() {
        val user = mockTest.getMockUser()
        val userJSON = mapper.writeValueAsString(user) as String

        every { service.updateUser(any(), any()) } just Runs

        mockMvc.perform(
            MockMvcRequestBuilders.put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(userJSON)
        ).andExpect(
            MockMvcResultMatchers.status().isNoContent
        )
    }

    @Test
    fun `Returns business validation exception when user is invalid when trying to update it`() {
        val user = mockTest.getMockUser()
        val userJSON = mapper.writeValueAsString(user) as String
        val exceptionMessage = "Invalid user"
        val errors = arrayListOf(
            Error(
                codigo = HttpStatus.BAD_REQUEST.value(),
                mensagem = exceptionMessage
            )
        )
        val expectedErrorResponse = ErrorResponse("Validation failed", errors)
        val expectedErrorJSON = mapper.writeValueAsString(expectedErrorResponse) as String

        every { service.updateUser(any(), any()) } throws BusinessValidationException(exceptionMessage)

        mockMvc.perform(
            MockMvcRequestBuilders.put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(userJSON)
        ).andExpect(
            MockMvcResultMatchers.status().isBadRequest
        ).andResultBodyMatches(json = expectedErrorJSON)
    }
}

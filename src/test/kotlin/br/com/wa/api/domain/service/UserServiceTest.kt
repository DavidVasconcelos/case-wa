package br.com.wa.api.domain.service

import br.com.wa.api.domain.model.User
import br.com.wa.api.exception.BusinessValidationException
import br.com.wa.api.exception.NotFoundException
import br.com.wa.api.mock.MockTest
import br.com.wa.api.support.annotations.CustomTest
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired

@CustomTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class UserServiceTest {

    @Autowired
    private lateinit var service: UserService

    @Autowired
    private lateinit var mockTest: MockTest

    private lateinit var expectedUser: User

    @BeforeAll
    fun setup() {
        val mockUser = mockTest.getMockUser()
        expectedUser = service.saveUser(mockUser)
    }

    @Test
    @Order(1)
    fun `Successful test find user`() {
        val savedUser = service.getById(expectedUser.id!!)
        expectedUser shouldBeEqualTo savedUser
    }

    @Test
    @Order(2)
    fun `when user does not exist throws not found exception`() {
        assertThrows<NotFoundException> {
            service.getById(1000000000)
        }
    }

    @Test
    @Order(3)
    fun `Successful test save user`() {
        val mockUser = mockTest.getMockUser()
        val savedUser = service.saveUser(mockUser)
        mockUser.nome shouldBeEqualTo savedUser.nome
        mockUser.documento shouldBeEqualTo savedUser.documento
    }

    @Test
    @Order(4)
    fun `when user is invalid throws business exception when trying to save it`() {
        val mockUserWithoutName = mockTest.getMockUserWithoutName()
        assertThrows<BusinessValidationException> {
            service.saveUser(mockUserWithoutName)
        }
    }

    @Test
    @Order(5)
    fun `Successful test update user`() {
        val mockUser = mockTest.getMockUser()
        service.updateUser(expectedUser.id!!, mockUser)
        mockUser.nome shouldNotBeEqualTo expectedUser.nome
        mockUser.documento shouldNotBeEqualTo expectedUser.documento
    }

    @Test
    @Order(6)
    fun `when user is invalid throws business exception when trying to update it`() {
        val mockUserWithoutDocument = mockTest.getMockUserWithoutDocument()
        assertThrows<BusinessValidationException> {
            service.updateUser(expectedUser.id!!, mockUserWithoutDocument)
        }
    }
}

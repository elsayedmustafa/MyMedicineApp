package com.example.mymedicineapp.auth.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseTest {

    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setup() {
        loginUseCase = LoginUseCase()
    }

    @Test
    fun `test login given valid credentials userName when invoke login then return LoginDomainModel`() {
        runTest {
            val result = loginUseCase.invoke(userName)
            assertEquals(LOGIN_DOMAIN_MODEL, result)
        }
    }
}
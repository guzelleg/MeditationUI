package com.erstegroup.george

import CoreProperties
import CoreProperties.currentCountry
import CoreProperties.currentTestInfo
import io.restassured.RestAssured
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class BaseTest {
    lateinit var token: String

    @BeforeAll
    fun `set up`(testInfo: TestInfo) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
    }


    @BeforeEach
    fun beforeTestHook(testInfo: TestInfo) {
        setCurrentCountry(testInfo)
    }

    @AfterAll
    fun `tear down`() {
        RestAssured.reset()
    }

    private fun setCurrentCountry(testInfo: TestInfo){
        currentTestInfo = testInfo
        val tags =  currentTestInfo.tags
        currentCountry = when {
            tags.contains("at") -> CoreProperties.Country.AT
            tags.contains("cz") -> CoreProperties.Country.CZ
            tags.contains("sk") -> CoreProperties.Country.SK
            else -> {
                throw RuntimeException("Please add the country tag to test (e.g.: at, sk, cz...)")
            }
        }
    }

}
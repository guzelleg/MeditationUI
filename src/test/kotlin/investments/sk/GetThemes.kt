package com.erstegroup.george.investments.sk

import CoreProperties.currentCountry
import com.erstegroup.george.BaseTest
import endpoints.InvestmentsUrls
import endpoints.InvestmentsUrls.getPipUrl
import io.restassured.RestAssured
import org.apache.http.HttpStatus
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import tdm.Tdm
import tokenRetrieval.BearerToken

class GetThemes : BaseTest() {

    @BeforeEach
    fun `get User Token`() {
        Tdm().getUserWithContext(currentCountry.toString(), type = "SHARE")
        token = BearerToken.getBearerToken(token = "0000")
    }

    @Test
    @Tags(Tag("sk"), Tag("investments"))
    fun `SK get pip themes returns status 200 and correct number of themes`() {
        RestAssured
            .given()
            .header("Authorization", token)

            .`when`()
            .get(getPipUrl(InvestmentsUrls.PipEndpoints.THEMES))

            .then()
            .statusCode(HttpStatus.SC_OK)
            .body("themes.size()", equalTo(4),
                "themes[0].title", equalTo("Global"),
                "themes[1].title", equalTo("Sustainability"),
                "themes[2].title", equalTo("Real assets"),
                "themes[3].title", equalTo("Innovation")
            )
    }
}
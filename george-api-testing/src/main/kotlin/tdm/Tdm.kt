package tdm

import CoreProperties
import io.restassured.RestAssured.given
import tdm.tdmModel.response.userbase.TdmUser
import tdm.tdmModel.request.TdmContext
import tdm.tdmModel.response.tdmContext.TdmContextResponse


class Tdm {
    val TDM_API_BASE_URL = "https://tdm-api.apps.g-labs.io"


    /*
    METHOD 1 : retrieve some random user with specific country
    */

    fun getUserByCountryFromUserbaseTable(country:String) {
        val response = given().get("$TDM_API_BASE_URL/userbase?limit=1&country=eq.$country").then().extract().path<List<TdmUser>>("").first()
        setUser(response)
    }

    private fun setUser(response: TdmUser) {
        CoreProperties.user = CoreProperties.RetrievedUserData(
            response.country,
            response.username,
            response.password,
            response.birthdate,
            response.token
        )
    }


    /*
        METHOD 2: create a tdm_context request as payload and get user from tdm_context
        Sample payload:
    {
        "country": "AT",
        "type": "FUND",
        "subtype": "BOND_FUND",
        "flags_ex": [
        "owner"
        ],
        "env": "fat"
    }*/

    fun getUserWithContext(
        country:String,
        type: String?,
        subtype: String? = null,
        flags_ex:List<String>? = listOf("owner"),
        env:String? = "dev",
        currency: String? = "EUR"
    ){
        val tdmContextBody = TdmContext.tdmContextBody(country, type,subtype,flags_ex,env,currency)

        var response =
        TdmContextResponse.tdmContext(given()
            .contentType("application/json")
            .body(tdmContextBody)
            .header("Prefer", "params=single-object")
            .post("$TDM_API_BASE_URL/rpc/get_context").then()
            .extract().response().asString())
        setUser(response)
    }

    private fun setUser(response: TdmContextResponse) {
        CoreProperties.user = CoreProperties.RetrievedUserData(
            response.country,
            response.username,
            response.password,
            response.birthdate,
            response.token
        )
    }
}
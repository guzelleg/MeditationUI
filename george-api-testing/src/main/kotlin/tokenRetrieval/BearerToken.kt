package tokenRetrieval

import CoreProperties
import io.restassured.RestAssured
import io.restassured.config.DecoderConfig
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.lang.invoke.MethodHandles


object BearerToken {
    private const val LOKI_BASE_URL ="https://loki.g-labs.io/token?"
    private var logger: Logger = LogManager.getLogger(MethodHandles.lookup().lookupClass())!!

    private fun getLokiBaseUrl(country:String, username: String, password: String, token: String?, birthDate: String?) =
        if (country == "cz") "${LOKI_BASE_URL}tenant=${country}&site=fat&user=$username&password=$password&otp=$token&birthDate=$birthDate"
        else "${LOKI_BASE_URL}tenant=${country}&site=fat&user=$username&password=$password&otp=$token"

    fun getBearerToken(
        country:String = CoreProperties.user.country,
        username: String = CoreProperties.user.username,
        password: String =CoreProperties.user.password,
        token: String? = CoreProperties.user.token,
        birthDate: String? = CoreProperties.user.birthdate
    ):String {
        val getRequestURL = getLokiBaseUrl(country.lowercase(), username, password, token, birthDate)
        logger.debug("Bearer token GET Request URL: $getRequestURL")
        RestAssured.config = RestAssured.config().decoderConfig(DecoderConfig.decoderConfig().noContentDecoders())

        val bearerToken: String = Given {
            header("Accept", "application/json")
        } When {
            get(getRequestURL)
        } Then {
            log().all()
            statusCode(200)
        } Extract {
            path("accessToken")
        }
        logger.debug("Bearer Token is: $bearerToken")
        return bearerToken
    }
}
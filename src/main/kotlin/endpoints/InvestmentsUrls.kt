package endpoints

import CoreProperties
import CoreProperties.currentCountry
import endpoints.Common.SK_BASE_URL
import endpoints.Common.STABLE_LANE_SK

object InvestmentsUrls {

    const val INVESTMENT_PUBLIC = "investment/public"
    const val PIP_URL = "/pip"

    enum class PipEndpoints(val value: String) {
        THEMES("themes")
    }

    fun getPipUrl(pipEndpoint:PipEndpoints): String =
        when (currentCountry) {
            CoreProperties.Country.SK -> "${SK_BASE_URL}$STABLE_LANE_SK$INVESTMENT_PUBLIC${PIP_URL}/${pipEndpoint.value}"
            else -> throw IllegalArgumentException("Specify ${pipEndpoint.value} Endpoint for $currentCountry")
        }

}
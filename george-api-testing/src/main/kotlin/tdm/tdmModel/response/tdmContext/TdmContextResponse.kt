package tdm.tdmModel.response.tdmContext

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class TdmContextResponse(
    val accountno_number: String?,
    val authorizationmethod: String?,
    val balance: String?,
    val birthdate: String?,
    val country: String,
    val currency: String,
    val customerid: String?,
    val description: String?,
    val envstatus: Envstatus,
    val features: List<String>,
    val flags: List<String>?,
    val id: String?,
    val isin: String?,
    val loginmethod: String?,
    val numberofshares: String?,
    val password: String,
    val precision: String?,
    val product: String?,
    val producti18n: String?,
    val profileflags: List<String>?,
    val settlementaccountiban: String?,
    val stockexchange: String?,
    val stockexchangecode: String?,
    val subtype: String?,
    val title: String?,
    val token: String,
    val token2: String?,
    val type: String?,
    val userid: Int?,
    val username: String,
    val webapi_id: String?
){
    companion object {
        fun tdmContext(string: String) = Json.decodeFromString<TdmContextResponse>(string)
    }
}
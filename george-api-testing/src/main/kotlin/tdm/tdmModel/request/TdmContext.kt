package tdm.tdmModel.request

import java.io.Serializable


data class TdmContext(
    val country: String,
    val type: String?,
    val subtype: String?,
    val flags_ex: List<String>?,
    val env: String?,
    val currency: String?,
): Serializable {
companion object {
    fun tdmContextBody(
        country: String,
        type: String?,
        subtype: String?,
        flags_ex: List<String>?,
        env: String?,
        currency: String?,
    ): TdmContext {
        return TdmContext(
            country, type,subtype,flags_ex,env,currency
        )
    }
}
}
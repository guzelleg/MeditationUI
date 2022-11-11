package tdm.tdmModel.response.tdmContext

import kotlinx.serialization.Serializable

@Serializable
data class Envstatus(
    val dev: Boolean,
    val fat: Boolean
)
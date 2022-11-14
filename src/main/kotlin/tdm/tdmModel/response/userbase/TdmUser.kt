package tdm.tdmModel.response.userbase

data class TdmUser(
    val authorizationmethod: String,
    val birthdate: String,
    val country: String,
    val customerid: String,
    val envs: List<String>,
    val flags: List<String>,
    val hostname: String,
    val lastupdate: String,
    val loginmethod: String,
    val not_after: String,
    val not_before: String,
    val overview_products_count: Int,
    val owner: String,
    val password: String,
    val roles: Any,
    val token: String,
    val token2: Any,
    val userid: Int,
    val username: String,
    val username_alias: Any
)
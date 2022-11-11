import org.junit.jupiter.api.TestInfo

    object CoreProperties {
        lateinit var currentCountry: Country
        lateinit var user: RetrievedUserData
        lateinit var currentTestInfo: TestInfo

        enum class Country(val shortcut: String) {
            AT("at"),
            CZ("cz"),
            SK("sk"),
            RO("ro"),
            HR("hr"),
            HU("hu");
        }

        data class RetrievedUserData(
            val country: String,
            val username:String,
            val password:String,
            val birthdate:String?,
            val token:String?
        )
    }


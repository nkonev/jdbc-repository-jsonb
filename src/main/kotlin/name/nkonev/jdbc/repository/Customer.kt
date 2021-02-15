package name.nkonev.jdbc.repository

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("customers")
data class Customer(
    @Id
    var id: Long? = null,
    var name: String = "",
    var personData: PersonData = PersonData(),
    var sessionData: SessionData = SessionData()
)

data class PersonData(
    var age: Int = 0,
    var petName: String = ""
)

data class SessionData(
    var token: String = "",
    var ttl: Long = 0
)
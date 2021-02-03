package name.nkonev.jdbc.repository

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("customers")
data class Customer(
    @Id
    var id: Long? = null,
    var name: String = "",
    var data: CustomerData = CustomerData()
)

data class CustomerData(
    var age: Int = 0,
    var petName: String = ""
)
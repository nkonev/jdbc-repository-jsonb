package name.nkonev.jdbc.repository

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Configuration
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import org.springframework.data.jdbc.core.convert.AbstractPostgresJsonReadingConverter
import org.springframework.data.jdbc.core.convert.AbstractPostgresJsonWritingConverter
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration

@Configuration
class JsonJdbcConfig(private val objectMapper: ObjectMapper) : AbstractJdbcConfiguration() {

    override fun userConverters(): List<*> {
        return mutableListOf(
                PersonDataWritingConverter(objectMapper), PersonDataReadingConverter(objectMapper),
                SessionDataWritingConverter(objectMapper), SessionDataReadingConverter(objectMapper)
        )
    }
}

@WritingConverter
class PersonDataWritingConverter(objectMapper: ObjectMapper) : AbstractPostgresJsonWritingConverter<PersonData>(objectMapper, true)

@ReadingConverter
class PersonDataReadingConverter(objectMapper: ObjectMapper) : AbstractPostgresJsonReadingConverter<PersonData>(objectMapper, PersonData::class.java)

@WritingConverter
class SessionDataWritingConverter(objectMapper: ObjectMapper) : AbstractPostgresJsonWritingConverter<SessionData>(objectMapper, true)

@ReadingConverter
class SessionDataReadingConverter(objectMapper: ObjectMapper) : AbstractPostgresJsonReadingConverter<SessionData>(objectMapper, SessionData::class.java)

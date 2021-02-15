package name.nkonev.jdbc.repository

import com.fasterxml.jackson.databind.ObjectMapper
import org.postgresql.util.PGobject
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration

@Configuration
class JsonJdbcConfig(private val objectMapper: ObjectMapper) : AbstractJdbcConfiguration() {

    @Bean
    override fun jdbcCustomConversions(): JdbcCustomConversions {
        return JdbcCustomConversions(listOf(
                PersonDataWritingConverter(objectMapper), PersonDataReadingConverter(objectMapper),
                SessionDataWritingConverter(objectMapper), SessionDataReadingConverter(objectMapper)
        ))
    }
}


open class AbstractJsonWritingConverter<T> (
        private val objectMapper: ObjectMapper
) : Converter<T, PGobject> {
    override fun convert(source: T): PGobject? {
        val jsonObject = PGobject()
        jsonObject.type = "json"
        jsonObject.value = objectMapper.writeValueAsString(source)
        return jsonObject
    }
}

open class AbstractJsonReadingConverter<T>(
        private val objectMapper: ObjectMapper,
        private val valueType: Class<T>
) : Converter<PGobject, T> {
    override fun convert(pgObject: PGobject): T {
        val source = pgObject.value
        return objectMapper.readValue(source, valueType)
    }
}

@WritingConverter
class PersonDataWritingConverter(objectMapper: ObjectMapper) : AbstractJsonWritingConverter<PersonData>(objectMapper)

@ReadingConverter
class PersonDataReadingConverter(objectMapper: ObjectMapper) : AbstractJsonReadingConverter<PersonData>(objectMapper, PersonData::class.java)

@WritingConverter
class SessionDataWritingConverter(objectMapper: ObjectMapper) : AbstractJsonWritingConverter<SessionData>(objectMapper)

@ReadingConverter
class SessionDataReadingConverter(objectMapper: ObjectMapper) : AbstractJsonReadingConverter<SessionData>(objectMapper, SessionData::class.java)

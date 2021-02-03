package name.nkonev.jdbc.repository

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import org.postgresql.util.PGobject
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.convert.CustomConversions
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration
import org.springframework.data.mapping.model.SimpleTypeHolder

@Configuration
class JsonJdbcConfig(private val objectMapper: ObjectMapper) : AbstractJdbcConfiguration() {

    @Bean
    override fun jdbcCustomConversions(): JdbcCustomConversions {
        return JdbcCustomConversions(listOf(EntityReadingConverter(objectMapper), EntityWritingConverter(objectMapper)))
    }
}

@WritingConverter
class EntityWritingConverter(
        private val objectMapper: ObjectMapper
) : Converter<CustomerData, PGobject> {
    override fun convert(source: CustomerData): PGobject? {
        val jsonObject = PGobject()
        jsonObject.type = "json"
        jsonObject.value = objectMapper.writeValueAsString(source)
        return jsonObject
    }
}

@ReadingConverter
class EntityReadingConverter(
        private val objectMapper: ObjectMapper
) : Converter<PGobject, CustomerData> {
    override fun convert(pgObject: PGobject): CustomerData {
        val source = pgObject.value
        return objectMapper.readValue(source, CustomerData::class.java)
    }
}

import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.http4k.core.Body
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.with
import org.http4k.format.ConfigurableJackson
import org.http4k.format.Jackson.auto
import org.http4k.format.asConfigurable
import org.http4k.lens.BiDiBodyLens
import org.junit.jupiter.api.Test


object CustomApiJson : ConfigurableJackson(
    KotlinModule.Builder()
        .build()
        .asConfigurable()
        .done())

context(CustomApiJson)
class Http4kTest {
    @Test
    fun `Just running HTTP4k`() {
        val testLens: BiDiBodyLens<String> = Body.auto<String>().toLens()
        Request(Method.POST, "/something")
            .with(testLens of "123")
    }
}
import com.loneoaktech.tests.shared.domain.model.ShoppingListItem
import com.loneoaktech.utilities.GenericItem
import com.quip.astor.android.brushlib.utility.summary
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class SharedLibTest {

    @Serializable
    data class TestBean( val id: String, val num: Int )

    @Test
    fun createListItem() {

        val item1 = ShoppingListItem(
            name = "Stuff",
            quantity = 2,
            description = "Something that we need."
        )

        println( "Item 1 = $item1")
    }

    @Test
    fun serializeListItem() {

        val item1 = ShoppingListItem(
            name = "Stuff",
            quantity = 2,
            description = "Something that we need."
        )

        val json = Json {
            prettyPrint = true
        }

        val item1Json = json.encodeToString( ShoppingListItem.serializer(), item1 )
        println("Item 1 json = $item1Json")
    }

    @Test
    fun serializeBean() {
        val json = Json {
            prettyPrint = true
        }

        val bean1 = TestBean("an-id", 123)

        val bean1json = json.encodeToString( TestBean.serializer(), bean1)
        println(bean1json)

    }

    @Test
    fun throwableExtensionTest() {

        val t = IllegalStateException("A test error")

        println("Our exception: ${t.summary()}")

    }

    @Test
    fun serializableObjectTest() {
        val gi = GenericItem(
            id = 12345,
            name = "item1",
            description = "the thing"
        )

        val formatter = Json { prettyPrint = true }

        println( formatter.encodeToString( GenericItem.serializer(), gi) )
    }
}
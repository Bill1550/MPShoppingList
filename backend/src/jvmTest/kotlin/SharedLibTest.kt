import com.loneoaktech.tests.shared.domain.model.ShoppingListItem
import kotlinx.serialization.json.Json
import kotlin.test.Test

class SharedLibTest {
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
}
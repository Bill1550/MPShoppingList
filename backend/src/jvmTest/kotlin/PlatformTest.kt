import com.loneoaktech.tests.shared.PlatformInfo
import kotlin.test.Test

/**
 * Created by BillH on 2/12/2021
 */
class PlatformTest {

    @Test
    fun printPlatformName() {
        println("-----> The platform is ${PlatformInfo.name}")
    }
}
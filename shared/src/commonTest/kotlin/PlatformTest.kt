import com.loneoaktech.tests.shared.PlatformInfo
import kotlin.test.Test


/**
 * Created by BillH on 2/12/2021
 */
class PlatformTest {
    @Test
    fun printPlatform() {
        println("----> the platform is ${PlatformInfo.name}")
    }
}
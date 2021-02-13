package basic

import com.loneoaktech.tests.shared.PlatformInfo
import org.junit.Test

/**
 * Created by BillH on 2/12/2021
 */
class PlatformTest {

    @Test
    fun printPlatformName() {
        println("------> The platform name is ${PlatformInfo.name}")
    }
}
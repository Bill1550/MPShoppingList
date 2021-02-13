package com.loneoaktech.tests.shared

/**
 * Created by BillH on 2/12/2021
 */
actual object PlatformInfo {
    actual val name: String
        get() = "Android ${android.os.Build.VERSION.SDK_INT}"
}
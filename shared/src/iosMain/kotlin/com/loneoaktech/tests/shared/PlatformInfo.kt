package com.loneoaktech.tests.shared

/**
 * Created by BillH on 2/12/2021
 */
actual object PlatformInfo {
    actual val name: String
        get() = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

}
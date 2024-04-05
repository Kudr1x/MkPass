package com.github.kudrix.mkpass.util.settings

import kotlinx.serialization.Serializable

@Serializable
data class SettingsData(
    val passwordLen: Int = 32,
    val masterPasswordHash: String = ""
)

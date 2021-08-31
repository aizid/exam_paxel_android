package com.aizidev.examapps.util

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

object GlobalFunc {

    @SuppressLint("HardwareIds")
    fun GET_DEVICE_ID(ctx: Context): String {
        return Settings.Secure.getString(
            ctx.applicationContext.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }

    fun GET_FORMAT_THOUSAND_SEPARATOR_ONE(value: Int?): String? {
        if (value == null || value == 0) return "0"
        val symbols = DecimalFormatSymbols(Locale.US)
        val formatter = DecimalFormat("#,###,###", symbols)
        return formatter.format(value)
    }
}
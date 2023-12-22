package com.semenchuk.junior.test.work.findthesameones.data.storage

import android.content.SharedPreferences

class AppStorage(
    private val sharedPreferences: SharedPreferences,
) {
    var minTime: Int
        get() = sharedPreferences.getInt(MIN_TIME_KEY, 0)
        set(value) = sharedPreferences.edit().putInt(MIN_TIME_KEY, value).apply()

    var availableCoins: Int
        get() = sharedPreferences.getInt(AVAILABLE_COINS_KEY, 0)
        set(value) = sharedPreferences.edit().putInt(AVAILABLE_COINS_KEY, value).apply()

    companion object {
        const val MIN_TIME_KEY = "minTime"
        const val AVAILABLE_COINS_KEY = "availableCoins"
    }
}
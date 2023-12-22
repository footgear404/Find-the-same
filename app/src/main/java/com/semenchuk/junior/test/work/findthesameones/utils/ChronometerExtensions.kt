package com.semenchuk.junior.test.work.findthesameones.utils

import android.os.SystemClock
import android.widget.Chronometer

fun Chronometer.timeInMilliseconds(): Long = SystemClock.elapsedRealtime() - this.base

fun Chronometer.timeInSeconds(): Int = ((SystemClock.elapsedRealtime() - this.base) / 1000).toInt()


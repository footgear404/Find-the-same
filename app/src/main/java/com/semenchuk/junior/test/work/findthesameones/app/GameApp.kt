package com.semenchuk.junior.test.work.findthesameones.app

import android.app.Application
import com.semenchuk.junior.test.work.findthesameones.di.dataModule
import com.semenchuk.junior.test.work.findthesameones.di.domainModule
import com.semenchuk.junior.test.work.findthesameones.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GameApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(androidContext = this@GameApp)
            modules(dataModule, domainModule, presentationModule)
        }
    }
}
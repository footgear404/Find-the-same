package com.semenchuk.junior.test.work.findthesameones.di

import android.content.Context
import android.content.SharedPreferences
import com.semenchuk.junior.test.work.findthesameones.data.CardRepositoryImpl
import com.semenchuk.junior.test.work.findthesameones.data.CoinsRepositoryImpl
import com.semenchuk.junior.test.work.findthesameones.data.storage.AppStorage
import com.semenchuk.junior.test.work.findthesameones.domain.CardRepository
import com.semenchuk.junior.test.work.findthesameones.domain.CoinsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<SharedPreferences> {
        androidContext()
            .getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }
    single<AppStorage> {
        AppStorage(sharedPreferences = get<SharedPreferences>())
    }

    single<CoinsRepository> {
        CoinsRepositoryImpl(appStorage = get<AppStorage>())
    }

    single<CardRepository> {
        CardRepositoryImpl()
    }
}
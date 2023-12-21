package com.semenchuk.junior.test.work.findthesameones.di

import com.semenchuk.junior.test.work.findthesameones.data.CardRepositoryImpl
import com.semenchuk.junior.test.work.findthesameones.data.CoinsRepositoryImpl
import com.semenchuk.junior.test.work.findthesameones.domain.CardRepository
import com.semenchuk.junior.test.work.findthesameones.domain.CoinsRepository
import org.koin.dsl.module

val dataModule = module {
    single<CoinsRepository> {
        CoinsRepositoryImpl()
    }

    single<CardRepository> {
        CardRepositoryImpl()
    }
}
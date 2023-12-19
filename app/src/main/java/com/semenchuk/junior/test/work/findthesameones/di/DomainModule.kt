package com.semenchuk.junior.test.work.findthesameones.di

import com.semenchuk.junior.test.work.findthesameones.domain.CoinsHandler
import com.semenchuk.junior.test.work.findthesameones.domain.CoinsRepository
import org.koin.dsl.module

val domainModule = module {
    single<CoinsHandler> {
        CoinsHandler(
            coinsRepository = get<CoinsRepository>()
        )
    }
}
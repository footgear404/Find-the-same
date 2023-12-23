package com.semenchuk.junior.test.work.findthesameones.di

import com.semenchuk.junior.test.work.findthesameones.domain.CardsHandler
import com.semenchuk.junior.test.work.findthesameones.domain.CoinsHandler
import com.semenchuk.junior.test.work.findthesameones.domain.GameStateHandler
import com.semenchuk.junior.test.work.findthesameones.domain.interfaces.CardRepository
import com.semenchuk.junior.test.work.findthesameones.domain.interfaces.CoinsRepository
import com.semenchuk.junior.test.work.findthesameones.domain.utils.CardAnimator
import org.koin.dsl.module

val domainModule = module {
    single<CoinsHandler> {
        CoinsHandler(
            coinsRepository = get<CoinsRepository>()
        )
    }
    single<CardsHandler> {
        CardsHandler(
            cardRepository = get<CardRepository>()
        )
    }

    single<GameStateHandler> {
        GameStateHandler(
            cardRepository = get<CardRepository>()
        )
    }

    single<CardAnimator> {
        CardAnimator()
    }
}
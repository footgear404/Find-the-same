package com.semenchuk.junior.test.work.findthesameones.di

import com.semenchuk.junior.test.work.findthesameones.domain.CardsHandler
import com.semenchuk.junior.test.work.findthesameones.domain.CoinsHandler
import com.semenchuk.junior.test.work.findthesameones.domain.GameStateHandler
import com.semenchuk.junior.test.work.findthesameones.presentation.ui.end_game.EndGameViewModel
import com.semenchuk.junior.test.work.findthesameones.presentation.ui.game_scene.GameSceneViewModel
import com.semenchuk.junior.test.work.findthesameones.presentation.ui.main_menu.MainMenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel<MainMenuViewModel> {
        MainMenuViewModel(
            coinsHandler = get<CoinsHandler>()
        )
    }

    viewModel<GameSceneViewModel> {
        GameSceneViewModel(
            coinsHandler = get<CoinsHandler>(),
            cardsHandler = get<CardsHandler>(),
            gameStateHandler = get<GameStateHandler>()
        )
    }

    viewModel<EndGameViewModel> {
        EndGameViewModel(
            coinsHandler = get<CoinsHandler>()
        )
    }
}
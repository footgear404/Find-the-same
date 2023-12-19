package com.semenchuk.junior.test.work.findthesameones.di

import com.semenchuk.junior.test.work.findthesameones.domain.CoinsHandler
import com.semenchuk.junior.test.work.findthesameones.presentation.ui.main_menu.MainMenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel<MainMenuViewModel> {
        MainMenuViewModel(
            coinsHandler = get<CoinsHandler>()
        )
    }
}
package com.semenchuk.junior.test.work.findthesameones.domain

sealed class State {
    data object AWAIT : State()
    data object WIN : State()
    data object PROGRESS : State()

}
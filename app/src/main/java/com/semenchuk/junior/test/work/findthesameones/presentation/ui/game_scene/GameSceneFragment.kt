package com.semenchuk.junior.test.work.findthesameones.presentation.ui.game_scene

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.semenchuk.junior.test.work.findthesameones.databinding.FragmentGameSceneBinding
import com.semenchuk.junior.test.work.findthesameones.domain.State.AWAIT
import com.semenchuk.junior.test.work.findthesameones.domain.State.PROGRESS
import com.semenchuk.junior.test.work.findthesameones.domain.State.WIN
import com.semenchuk.junior.test.work.findthesameones.presentation.ui.BaseFragment
import com.semenchuk.junior.test.work.findthesameones.presentation.ui.game_scene.adapter.GameFieldAdapter
import com.semenchuk.junior.test.work.findthesameones.utils.timeInSeconds
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameSceneFragment : BaseFragment<FragmentGameSceneBinding>() {

    private val gameSceneViewModel by viewModel<GameSceneViewModel>()
    private val gameFieldAdapter by lazy { GameFieldAdapter(::onCellClickListener) }

    override fun initBinding(inflater: LayoutInflater): FragmentGameSceneBinding =
        FragmentGameSceneBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flowObserver(gameSceneViewModel.currentReward) { reward ->
            binding.coinsStorageView.coinsCount.text =
                gameSceneViewModel.currentReward.value.toString()
            Log.d("REWARD", "reward: $reward")
        }

        flowObserver(gameSceneViewModel.gameState) { state ->
            when (state) {
                AWAIT -> {
                    initGameField()
                    initTimer()
                }

                PROGRESS -> startGame()
                WIN -> stopGame()
            }
            Log.d("STATE", "state: ${state.javaClass.name}")
        }
    }

    private fun startGame() {
        binding.timerView.timer.start()
        binding.timerView.timer.base = SystemClock.elapsedRealtime()
    }

    private fun stopGame() {
        binding.timerView.timer.stop()
        val reward = gameSceneViewModel.currentReward.value
        gameSceneViewModel.saveReward(reward)
        snack("You are won! Reward $reward", binding.root)
    }

    private fun initGameField() {
        binding.gameField.adapter = gameFieldAdapter
        flowObserver(gameSceneViewModel.cards) { cardList ->
            gameFieldAdapter.submitData(data = cardList)
        }
    }

    private fun initTimer() {
        binding.timerView.timer.setOnChronometerTickListener { chronometer ->
            gameSceneViewModel.setTime(timeInSeconds = chronometer.timeInSeconds())
        }

        flowObserver(gameSceneViewModel.timer) { timeInSeconds ->
            Log.d("TIMER", "timer: $timeInSeconds")
            gameSceneViewModel.calculateReward(
                timeInSeconds = gameSceneViewModel.timer.value ?: 0
            )
        }
    }

    private fun onCellClickListener(position: Int) {
        val updatedCardPosition = gameSceneViewModel.flipCard(position)
        gameFieldAdapter.notifyItemChanged(updatedCardPosition)
    }
}



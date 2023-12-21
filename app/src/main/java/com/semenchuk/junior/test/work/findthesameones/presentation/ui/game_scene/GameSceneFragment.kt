package com.semenchuk.junior.test.work.findthesameones.presentation.ui.game_scene

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.semenchuk.junior.test.work.findthesameones.databinding.FragmentGameSceneBinding
import com.semenchuk.junior.test.work.findthesameones.domain.State
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

        flowObserver(gameSceneViewModel.gameState) {
            Log.d("STATE", "state: ${it.javaClass.name}")
            when (it) {
                State.AWAIT -> initGameField()
                State.PROGRESS -> startGame()
                State.WIN -> {
//                    binding.timerView.timer.stop()
                    gameSceneViewModel.takeReward(
                        timeInSeconds = gameSceneViewModel.timer.value ?: 0
                    )
                }
            }
        }

        flowObserver(gameSceneViewModel.cards) { cardList ->
            gameFieldAdapter.submitData(data = cardList)
        }

        flowObserver(gameSceneViewModel.timer) { timeInSeconds ->
            timeInSeconds.let {
                Log.d("TIMER", "timer: $it")
            }
        }

        flowObserver(gameSceneViewModel.coins) { coins ->
            coins.let { value ->
                Log.d("COINS", "coins: $value")
                snack("You are won! Reward $value", binding.root)
            }
        }

        flowObserver(gameSceneViewModel.currentReward) { currentReward ->
            currentReward.let {value ->
                binding.rewardView.coinsCount.text = value.toString()
                Log.d("REWARD", "reward: $value")
            }
        }
    }

    private fun initGameField() {
        binding.gameField.adapter = gameFieldAdapter
    }

    private fun startGame() {
        binding.timerView.timer.setOnChronometerTickListener { chronometer ->
            gameSceneViewModel.setTime(timeInSeconds = chronometer.timeInSeconds())
        }
        binding.timerView.timer.start()
    }

    private fun onCellClickListener(position: Int) {
        val updatedCardPosition = gameSceneViewModel.flipCard(position)
        gameFieldAdapter.notifyItemChanged(updatedCardPosition)
    }
}



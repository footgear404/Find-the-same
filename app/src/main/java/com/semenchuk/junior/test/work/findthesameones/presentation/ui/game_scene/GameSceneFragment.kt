package com.semenchuk.junior.test.work.findthesameones.presentation.ui.game_scene

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.semenchuk.junior.test.work.findthesameones.databinding.FragmentGameSceneBinding
import com.semenchuk.junior.test.work.findthesameones.presentation.ui.BaseFragment
import com.semenchuk.junior.test.work.findthesameones.utils.timeInSeconds
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameSceneFragment : BaseFragment<FragmentGameSceneBinding>() {

    private val gameSceneViewModel by viewModel<GameSceneViewModel>()
    override fun initBinding(inflater: LayoutInflater): FragmentGameSceneBinding =
        FragmentGameSceneBinding.inflate(inflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.timerView.timer.setOnChronometerTickListener { chronometer ->
            gameSceneViewModel.setTime(timeInSeconds = chronometer.timeInSeconds())
        }

        binding.timerView.timer.start()

        flowObserver(gameSceneViewModel.timer) { timeInSeconds ->
            timeInSeconds.let {
                Log.d("TIMER", "timer: $it")
                gameSceneViewModel.takeReward(timeInSeconds = it ?: 0)
            }
        }

        flowObserver(gameSceneViewModel.coins) { coins ->
            coins.let {
                Log.d("COINS", "coins: $it")
            }
        }

        flowObserver(gameSceneViewModel.currentReward) { currentReward ->
            currentReward.let {
                Log.d("REWARD", "reward: $it")
            }
        }
    }
}
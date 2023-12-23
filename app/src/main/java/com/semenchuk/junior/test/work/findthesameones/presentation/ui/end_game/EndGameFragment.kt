package com.semenchuk.junior.test.work.findthesameones.presentation.ui.end_game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.semenchuk.junior.test.work.findthesameones.databinding.FragmentEndGameBinding
import com.semenchuk.junior.test.work.findthesameones.presentation.ui.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class EndGameFragment : BaseFragment<FragmentEndGameBinding>() {

    private val endGameViewModel by viewModel<EndGameViewModel>()
    private val args by navArgs<EndGameFragmentArgs>()
    override fun initBinding(inflater: LayoutInflater): FragmentEndGameBinding =
        FragmentEndGameBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rewardedCoins = args.coins
        Log.d("TAG", "rewardedCoins: $rewardedCoins")

        binding.rewardView.coinsCount.text = rewardedCoins.toString()

        viewLifecycleOwner.lifecycleScope.launch {
            endGameViewModel.updateReward(rewardedCoins)
        }

        binding.doubleReward.setOnClickListener {
            binding.rewardView.coinsCount.text = (rewardedCoins * 2).toString()
            endGameViewModel.updateReward(rewardedCoins).toString()
            it.isEnabled = false
        }

        binding.btnHome.setOnClickListener {
            val toMainMenu = EndGameFragmentDirections.actionEndGameToMainMenu()
            navigate(toMainMenu)
        }
    }
}
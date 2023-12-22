package com.semenchuk.junior.test.work.findthesameones.presentation.ui.main_menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.semenchuk.junior.test.work.findthesameones.R
import com.semenchuk.junior.test.work.findthesameones.databinding.FragmentMainMenuBinding
import com.semenchuk.junior.test.work.findthesameones.presentation.ui.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainMenuFragment : BaseFragment<FragmentMainMenuBinding>() {

    private val mainMenuViewModel by viewModel<MainMenuViewModel>()

    override fun initBinding(inflater: LayoutInflater): FragmentMainMenuBinding =
        FragmentMainMenuBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            mainMenuViewModel.coins.collect { coinsCount ->
                binding.coinsView.coinsCount.text = coinsCount.toString()
                Log.d("TAG", "coinsCount: $coinsCount")
            }
        }

        with(binding) {
            btnPlay.setOnClickListener {
                navigate(R.id.action_main_menu_to_game_scene)
            }
            btnSafe.setOnClickListener {
                snack(getString(R.string.safe_btn_message), it)
            }

            coinsView.coinImg.setOnClickListener {
                mainMenuViewModel.resetCoins()
            }
        }
    }
    override fun onResume() {
        super.onResume()
        mainMenuViewModel.updateCoinsView()
    }
}
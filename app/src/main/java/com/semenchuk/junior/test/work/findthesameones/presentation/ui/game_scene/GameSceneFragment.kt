package com.semenchuk.junior.test.work.findthesameones.presentation.ui.game_scene

import android.view.LayoutInflater
import com.semenchuk.junior.test.work.findthesameones.databinding.FragmentGameSceneBinding
import com.semenchuk.junior.test.work.findthesameones.presentation.ui.BaseFragment

class GameSceneFragment : BaseFragment<FragmentGameSceneBinding>() {
    override fun initBinding(inflater: LayoutInflater): FragmentGameSceneBinding =
        FragmentGameSceneBinding.inflate(inflater)
}
package com.semenchuk.junior.test.work.findthesameones.domain.utils

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View

class CardAnimator {
    fun rotateCard(
        view: View,
        fromDegrees: Float,
        toDegrees: Float,
        onAnimationHalfway: () -> Unit,
        onAnimationEnd: () -> Unit,
    ) {
        val rotateAnim = ObjectAnimator.ofFloat(view, "rotationY", fromDegrees, toDegrees)
        rotateAnim.duration = 300
        rotateAnim.addUpdateListener { animation ->
            val animatedFraction = animation.animatedFraction
            if (animatedFraction >= 0.5) {
                onAnimationHalfway.invoke()
            }
        }
        rotateAnim.addListener(object : Animator.AnimatorListener {

            override fun onAnimationStart(p0: Animator) {
            }

            override fun onAnimationEnd(p0: Animator) {
                onAnimationEnd.invoke()
            }

            override fun onAnimationCancel(p0: Animator) {
            }

            override fun onAnimationRepeat(p0: Animator) {
            }
        })
        rotateAnim.start()
    }
}
package kr.loner.fx.ui.dest.main

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kr.loner.fx.R
import kr.loner.fx.databinding.FragmentGameBinding
import kr.loner.fx.ui.activity.GameActivity
import kr.loner.fx.ui.base.BaseFragment
import kr.loner.fx.viewmodel.MainViewModel

class GameFragment : BaseFragment<FragmentGameBinding>(
    R.layout.fragment_game,
    MainViewModel::class.java
) {
    @SuppressLint("RestrictedApi")
    override fun FragmentGameBinding.setDataBind() {
        //툴바 설정
        (activity as AppCompatActivity?)!!.supportActionBar!!.apply {
            setShowHideAnimationEnabled(false)
            hide()
        }

        ivGameTitleImage.animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.anim_gametitle)

        vm!!.userData.observe(viewLifecycleOwner, { vm!!.name = it.name })

        btnGameStart.setOnClickListener {
            goToTheGame(false)
        }
        btnRecordCheck.setOnClickListener {
            goToTheGame(true)
        }
    }

    private fun FragmentGameBinding.goToTheGame(goToTheRecode: Boolean) {
        Intent(requireActivity(), GameActivity::class.java).also {
            it.putExtra("name", vm!!.name)
            it.putExtra("goToTheRecord", goToTheRecode)
            if(goToTheRecode)
                it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(it)
        }
    }
}
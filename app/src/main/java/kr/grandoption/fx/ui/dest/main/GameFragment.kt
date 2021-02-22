package kr.grandoption.fx.ui.dest.main

import android.annotation.SuppressLint
import android.content.Intent
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kr.grandoption.fx.R
import kr.grandoption.fx.databinding.FragmentGameBinding
import kr.grandoption.fx.ui.activity.GameActivity
import kr.grandoption.fx.ui.base.BaseFragment
import kr.grandoption.fx.viewmodel.MainViewModel

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

        btnGameStart.setOnClickListener { goToTheGame(false) }
        btnRecordCheck.setOnClickListener { goToTheGame(true) }
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
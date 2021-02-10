package kr.loner.fx.ui.dest.main

import android.annotation.SuppressLint
import android.content.Intent
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
        (activity as AppCompatActivity?)!!.supportActionBar!!.apply {
            setShowHideAnimationEnabled(false)
            hide()
        }

            btnGameStart.setOnClickListener {
                startActivity(Intent(requireActivity(), GameActivity::class.java))
            }
        }
    }
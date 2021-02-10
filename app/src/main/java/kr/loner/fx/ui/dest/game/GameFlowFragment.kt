package kr.loner.fx.ui.dest.game

import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.loner.fx.R
import kr.loner.fx.databinding.FragmentGameFlowBinding
import kr.loner.fx.ui.adapter.GameAdapter
import kr.loner.fx.ui.base.BaseFragment
import kr.loner.fx.ui.util.Contents
import kr.loner.fx.viewmodel.GameViewModel
import kr.loner.fx.viewmodel.MainViewModel

class GameFlowFragment:BaseFragment<FragmentGameFlowBinding>(R.layout.fragment_game_flow,
GameViewModel::class.java) {

    private val args by lazy {
        GameFlowFragmentArgs.fromBundle(
            requireArguments()
        )
    }
    private val dp by lazy { resources.displayMetrics.density }

    private val finalRound by lazy{args.maxRound}
    private val row by lazy{args.row}
    private val col by lazy{args.col}


    override fun FragmentGameFlowBinding.setDataBind() {
        currentRound = Contents.startRound
        maxRound = finalRound

        getScreenSize()
        setRecyclerView()

        lifecycleScope.launch {
            delay(2000)
            previewCorrectAnswer()

            delay(4000)
            gameInProgress()
        }
    }

    private fun FragmentGameFlowBinding.getScreenSize() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val metrics = DisplayMetrics()
            requireActivity().display?.getRealMetrics(metrics)
            vm!!.displayWidth = metrics.widthPixels

        } else {
            val display = requireActivity().windowManager.defaultDisplay
            val size = Point()
            display.getRealSize(size)
            vm!!.displayWidth = size.x
        }
    }


    private fun FragmentGameFlowBinding.setRecyclerView() {

        rvGameFlow.adapter =
            GameAdapter(
                gameSettingPosition = Contents.GAME_READY_MODE,
                row = row,
                col = col,
                maxRound = finalRound,
                clickEvnet_timeBarAniReStart =
                {
                    timeBarAnimationCancel(binding)
                    timeBarAnimation(binding, dp)
                },
                clickEvnet_answerIsWrongInClickEvent =
                {
                    answerIsWrong(
                        binding,
                        { previewCorrectAnswer() },
                        { gameInProgress() })
                },
                clickEvnet_goToTheNextRound = {
                    goToTheNextRound(
                        binding,
                        { previewCorrectAnswer() },
                        { gameInProgress() }
                    )
                },
                clickEvnet_afterTheLastGameEvent = {
                    afterTheLastGameEvent(
                        this@GameFlowFragment,
                        finalRound, row, col
                    )
                }
            )

        rvGameFlow.layoutManager = GridLayoutManager(requireContext(), row)

    }



    private fun previewCorrectAnswer() {

        binding.tvInfo.text = resources.getString(R.string.tv_remember)
        timeBarAnimation(binding,dp)

        (binding.rvGameFlow.adapter as GameAdapter).run {
            gameSettingPosition = Contents.GAME_REMEMBER_MODE
            notifyDataSetChanged()
        }
    }

    private fun gameInProgress() {
        binding.apply {

            tvInfo.text = resources.getString(R.string.tv_find)
            ivImageCheck.visibility = View.VISIBLE
            ivImageCheck.setBackgroundResource(R.drawable.select_image_result)

            (rvGameFlow.adapter as GameAdapter).run {
                gameSettingPosition = Contents.GAME_START_MODE
                notifyDataSetChanged()
            }
        }
    }

    private fun timeBarAnimation(binding: FragmentGameFlowBinding, dp: Float) {

        binding.viewTimeBar.animate().apply {
            translationXBy(0f)
            translationX(-binding.vm!!.displayWidth / 1.92f * dp)
            interpolator = LinearInterpolator()
            duration = Contents.GAME_ROUND_TIME
        }.start()
    }

    private fun timeBarAnimationCancel(binding: FragmentGameFlowBinding) {

        binding.apply {
            viewTimeBar.animate().cancel()
            viewTimeBar.animate().translationX(0f).duration = 10

        }
    }

    private fun answerIsWrong(
        binding: FragmentGameFlowBinding, previewCorrectAnswer: () -> Unit,
        gameInProgress: () -> Unit
    ) {

        binding.apply {
            timeBarAnimationCancel(binding)

            tvInfo.text = getString(R.string.tv_wrongAnswer)
            ivImageCheck.visibility = View.GONE
            layoutInfo.background =
                getDrawable(requireContext(),R.drawable.background_wrong_answer)

            lifecycleScope.launch {
                delay(2000)
                (rvGameFlow.adapter as GameAdapter).run {
                    gameSettingPosition = Contents.GAME_READY_MODE
                    layoutInfo.background =
                        getDrawable(requireContext(),R.drawable.background_info)
                    tvInfo.text = resources.getString(R.string.tv_gameReady)
                    notifyDataSetChanged()

                    delay(2000)
                    previewCorrectAnswer()
                    delay(4000)
                    gameInProgress()
                }
            }
        }
    }

    private fun goToTheNextRound(
        binding: FragmentGameFlowBinding, previewCorrectAnswer: () -> Unit,
        gameInProgress: () -> Unit
    ) {
        binding.apply {
            timeBarAnimationCancel(binding)
            Contents.startRound++

            currentRound = Contents.startRound
            ivImageCheck.visibility = View.GONE
            layoutInfo.background =
                getDrawable(requireContext(),R.drawable.background_answer)
            tvInfo.text = getString(R.string.tv_answer)

            lifecycleScope.launch {
                delay(2000)

                (rvGameFlow.adapter as GameAdapter).run {
                    gameSettingPosition = Contents.GAME_READY_MODE
                    layoutInfo.background =
                        getDrawable(requireContext(),R.drawable.background_info)
                    tvInfo.text = getString(R.string.tv_gameReady)
                    notifyDataSetChanged()

                    delay(2000)
                    previewCorrectAnswer()

                    delay(4000)
                    gameInProgress()

                }

            }
        }
    }

    private fun afterTheLastGameEvent(fragment: Fragment, finalRound: Int, row: Int, col: Int) {
        fragment.findNavController().navigate(R.id.action_gameFlowFragment_to_gameRecordFragment)
        binding.vm!!.insert(finalRound, row, col)
    }

}






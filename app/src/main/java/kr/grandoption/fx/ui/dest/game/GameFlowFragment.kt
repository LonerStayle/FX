package kr.grandoption.fx.ui.dest.game

import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.Timestamp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.grandoption.fx.R
import kr.grandoption.fx.databinding.FragmentGameFlowBinding
import kr.grandoption.fx.db.entity.GameModel
import kr.grandoption.fx.ui.adapter.GameAdapter
import kr.grandoption.fx.ui.base.BaseFragment
import kr.grandoption.fx.ui.util.Contents
import kr.grandoption.fx.ui.util.Contents.startRound
import kr.grandoption.fx.viewmodel.GameViewModel

class GameFlowFragment : BaseFragment<FragmentGameFlowBinding>(
    R.layout.fragment_game_flow,
    GameViewModel::class.java
) {

    private val args by lazy {
        GameFlowFragmentArgs.fromBundle(
            requireArguments()
        )
    }
    private val dp by lazy { resources.displayMetrics.density }
    private val finalRound by lazy { args.maxRound }
    private val row by lazy { args.row }
    private val col by lazy { args.col }


    override fun FragmentGameFlowBinding.setDataBind() {
        currentRound = startRound
        maxRound = finalRound
        vm!!.userName = requireActivity().intent.getStringExtra("name")
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
                    timeBarAnimationCancel()
                    timeBarAnimation(dp)
                },
                clickEvnet_answerIsWrongInClickEvent =
                {
                    answerIsWrong(
                        { previewCorrectAnswer() },
                        { gameInProgress() })
                },
                clickEvnet_goToTheNextRound = {
                    goToTheNextRound(
                        { previewCorrectAnswer() },
                        { gameInProgress() }
                    )
                },
                clickEvnet_afterTheLastGameEvent = {
                    afterTheLastGameEvent(
                        finalRound, row, col, vm!!.userName!!,
                        vm!!.score
                    )
                }
            )

        rvGameFlow.layoutManager = GridLayoutManager(requireContext(), row)

    }


    private fun FragmentGameFlowBinding.previewCorrectAnswer() {

        if (context != null)
            tvInfo.text = resources.getString(R.string.tv_remember)

        timeBarAnimation(dp)

        (rvGameFlow.adapter as GameAdapter).run {
            gameSettingPosition = Contents.GAME_REMEMBER_MODE
            notifyDataSetChanged()
        }
    }

    private fun FragmentGameFlowBinding.gameInProgress() {
        tvInfo.text = resources.getString(R.string.tv_find)
        ivImageCheck.visibility = View.VISIBLE
        ivImageCheck.setBackgroundResource(R.drawable.select_image_result)

        (rvGameFlow.adapter as GameAdapter).run {
            gameSettingPosition = Contents.GAME_START_MODE
            notifyDataSetChanged()
        }

    }

    private fun FragmentGameFlowBinding.timeBarAnimation(dp: Float) {

        viewTimeBar.animate().apply {

            translationX(-binding.vm!!.displayWidth / 1.92f * dp)
            interpolator = LinearInterpolator()
            duration = Contents.GAME_ROUND_TIME
        }.withEndAction {
            (rvGameFlow.adapter as GameAdapter).run {
                if (gameSettingPosition == Contents.GAME_START_MODE) {

                    if (context != null)
                        tvInfo.text = getString(R.string.tv_timeOver)

                    ivImageCheck.visibility = View.GONE
                    gameSettingPosition = Contents.GAME_READY_MODE
                    notifyDataSetChanged()
                    timeBarAnimationCancel()
                    lifecycleScope.launch {
                        delay(2000)
                        previewCorrectAnswer()

                        delay(3000)
                        gameInProgress()
                    }
                }
            }
        }.start()
    }

    private fun FragmentGameFlowBinding.timeBarAnimationCancel() {
        viewTimeBar.animate().cancel()
        viewTimeBar.animate().translationX(0f).duration = 10

    }

    private fun FragmentGameFlowBinding.answerIsWrong(
        previewCorrectAnswer: () -> Unit,
        gameInProgress: () -> Unit
    ) {


        timeBarAnimationCancel()

        tvInfo.text = getString(R.string.tv_wrongAnswer)
        ivImageCheck.visibility = View.GONE
        layoutInfo.background =
            getDrawable(requireContext(), R.drawable.background_wrong_answer)

        lifecycleScope.launch {
            delay(2000)
            (rvGameFlow.adapter as GameAdapter).run {
                gameSettingPosition = Contents.GAME_READY_MODE
                layoutInfo.background =
                    getDrawable(requireContext(), R.drawable.background_info)
                tvInfo.text = resources.getString(R.string.tv_gameReady)
                notifyDataSetChanged()

                delay(2000)
                previewCorrectAnswer()
                delay(3500)
                gameInProgress()
            }
        }

    }

    private fun FragmentGameFlowBinding.goToTheNextRound(
        previewCorrectAnswer: () -> Unit,
        gameInProgress: () -> Unit
    ) {

        timeBarAnimationCancel()
        startRound++
        vm!!.score += col * row

        currentRound = startRound
        ivImageCheck.visibility = View.GONE
        layoutInfo.background =
            getDrawable(requireContext(), R.drawable.background_answer)
        tvInfo.text = getString(R.string.tv_answer)

        lifecycleScope.launch {
            delay(2000)

            (rvGameFlow.adapter as GameAdapter).run {
                gameSettingPosition = Contents.GAME_READY_MODE
                layoutInfo.background =
                    getDrawable(requireContext(), R.drawable.background_info)
                tvInfo.text = getString(R.string.tv_gameReady)
                notifyDataSetChanged()

                delay(2000)
                previewCorrectAnswer()

                delay(4000)
                gameInProgress()

            }

        }

    }

    private fun afterTheLastGameEvent(
        finalRound: Int,
        row: Int,
        col: Int,
        name: String,
        score: Int
    ) {

        val game = GameModel(
            round = finalRound, row = row, col = col, myName = name, score = score,
            timeStamp = Timestamp.now().toDate()
        )


        binding.vm!!.insert(game)
        binding.vm!!.insertRanking(game)

        findNavController().navigate(R.id.action_gameFlowFragment_to_gameRankingFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        startRound = 1
        binding.vm!!.score = 0
    }
}

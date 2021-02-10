package kr.loner.fx.ui.dest.main


import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import kr.loner.fx.R
import kr.loner.fx.databinding.FragmentShareBinding
import kr.loner.fx.ui.base.BaseFragment
import kr.loner.fx.viewmodel.MainViewModel

class ShareFragment : BaseFragment<FragmentShareBinding>(R.layout.fragment_share,
MainViewModel::class.java) {
    @SuppressLint("RestrictedApi")
    override fun FragmentShareBinding.setDataBind() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.apply {
            setShowHideAnimationEnabled(false)
            hide()
        }

        btnGoToTheHomePage.setOnClickListener { goToTheWeb(getString(R.string.homePageLink)) }
        btnInstarShare.setOnClickListener {goToTheWeb(getString(R.string.instarLink))  }
        btnKakaoShare.setOnClickListener {goToTheWeb(getString(R.string.kakaoLink))  }
        btnTelegramShare.setOnClickListener {goToTheWeb(getString(R.string.telegramLink))  }
        btnTwitterShere.setOnClickListener {goToTheWeb(getString(R.string.twitterLink))  }
        btnRegister.setOnClickListener {goToTheWeb(getString(R.string.registerLink))  }


    }
    private fun goToTheWeb(webSite:String){
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(webSite)))
    }


}
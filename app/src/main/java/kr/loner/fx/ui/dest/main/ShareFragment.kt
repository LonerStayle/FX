package kr.loner.fx.ui.dest.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.loner.fx.databinding.FragmentShareBinding

class ShareFragment : Fragment() {

    lateinit var binding:FragmentShareBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentShareBinding.inflate(layoutInflater,container,false).run{
        binding = this
        root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}
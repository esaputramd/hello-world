package com.d3if0065.helloworld


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.d3if0065.helloworld.databinding.FragmentAboutBinding
import com.d3if0065.helloworld.databinding.FragmentFragment2Binding

/**
 * A simple [Fragment] subclass.
 */
class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentAboutBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_about,container,false)
        return binding.root
    }


}

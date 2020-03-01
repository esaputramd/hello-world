package com.d3if0065.helloworld


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.d3if0065.helloworld.databinding.FragmentBlankBinding

/**
 * A simple [Fragment] subclass.
 */
class BlankFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentBlankBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false)
        // Inflate the layout for this fragment


        binding.textView3.setOnClickListener { view: View ->
            view.findNavController().navigate(
                R.id.action_blankFragment_to_fragment2
            )
        }

        binding.btnMenuDua.setOnClickListener{
            view : View ->
            view.findNavController().navigate(
                R.id.action_blankFragment_to_fragment3
            )
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overlow_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,view!!.findNavController()) || super.onOptionsItemSelected(item)
    }
}

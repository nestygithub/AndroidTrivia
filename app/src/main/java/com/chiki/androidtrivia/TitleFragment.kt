package com.chiki.androidtrivia

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.chiki.androidtrivia.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    //Binding
    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding!!

    //LifeCycle Methods
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTitleBinding.inflate(inflater,container,false)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playButton.setOnClickListener {
            navigateToGameFragment()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, findNavController())|| super.onOptionsItemSelected(item)
    }

    //Actions
    private fun navigateToGameFragment(){
        val action = TitleFragmentDirections.actionTitleFragmentToGameFragment()
        findNavController().navigate(action)
    }


}
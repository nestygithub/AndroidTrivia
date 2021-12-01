package com.chiki.androidtrivia

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.navigation.fragment.findNavController
import com.chiki.androidtrivia.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {
    //Binding
    private var _binding: FragmentGameWonBinding? = null
    private val binding get() = _binding!!

    //Lifecycle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentGameWonBinding.inflate(inflater,container,false)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextMatchButton.setOnClickListener {
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
        inflater.inflate(R.menu.winner_menu,menu)
        if(null == getShareIntent().resolveActivity(requireActivity().packageManager)){
            menu.findItem(R.id.share).isVisible = false
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

    //Actions
    private fun navigateToGameFragment(){
        val action = GameWonFragmentDirections.actionGameWonFragmentToGameFragment()
        findNavController().navigate(action)
    }

    private fun shareSuccess(){
        startActivity(getShareIntent())
    }

    private fun getShareIntent(): Intent {
        //Args
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        return ShareCompat.IntentBuilder.from(requireActivity())
            .setText(getString(R.string.share_success_text,args.numCorrect,args.numQuestions))
            .setType("text/plain")
            .intent
    }
}
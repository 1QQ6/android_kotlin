package com.example.xlwapp.fragment.guessgames

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.xlwapp.R
import com.example.xlwapp.databinding.FragmentGuessScoreBinding
import com.example.xlwapp.viewmodel.guessgame.Factory.GameScoreViewModelFactory
import com.example.xlwapp.viewmodel.guessgame.GameScoreViewModel

/**
 * A simple [Fragment] subclass.
 */
class GuessScoreFragment : Fragment() {

    private lateinit var scoreViewModel: GameScoreViewModel
    private lateinit var scoreViewModelFactory:GameScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = GuessScoreFragmentArgs.fromBundle(arguments!!)
        scoreViewModelFactory = GameScoreViewModelFactory(args.score)
        scoreViewModel = ViewModelProvider(this,scoreViewModelFactory).get(GameScoreViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentGuessScoreBinding>(inflater,R.layout.fragment_guess_score,container,false)
        binding.guessScoreViewModel = scoreViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        scoreViewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.score.text = newScore.toString()
        })
        scoreViewModel.eventPlayAgain.observe(viewLifecycleOwner, Observer { playAgain ->
            if(playAgain){
                findNavController().navigate(GuessScoreFragmentDirections.actionGuessScoreFragmentToGuessGameFragment2())
                scoreViewModel.onPlayAgainFinish()
            }
        })
        return binding.root
    }

}

package com.example.laboratorio5apps.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.laboratorio5apps.R
import com.example.laboratorio5apps.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
/**
* @author Olivverde
* In cooperation with other students from Computer Science career:
* @julioH
* Class purpose: Home fragment
*/
@Suppress("UNREACHABLE_CODE")
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)
        binding.fab.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_nav_home_to_nav_add_question)
        }
        binding.btnQuiz.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER") { view:View ->
            view.findNavController().navigate(R.id.action_nav_home_to_nav_question)
        }
        //this at finish
        return binding.root
    }
}
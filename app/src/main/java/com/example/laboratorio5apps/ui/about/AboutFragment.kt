package com.example.laboratorio5apps.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.laboratorio5apps.R
import com.example.laboratorio5apps.databinding.FragmentAboutBinding
import com.example.laboratorio5apps.databinding.FragmentAllQuestionsBinding

class AboutFragment : Fragment() {

    private lateinit var aboutViewModel: AboutViewModel
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutViewModel =
            ViewModelProviders.of(this).get(AboutViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentAboutBinding>(
            inflater, R.layout.fragment_about, container, false)
        binding.model = aboutViewModel
        return binding.root
    }
}
package com.example.laboratorio5apps.ui.answereds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio5apps.R
import com.example.laboratorio5apps.databinding.FragmentAnsweredsBinding

class AnsweredsFragment : Fragment() {

    private lateinit var answeredsViewModel: AnsweredsViewModel
    private lateinit var binding: FragmentAnsweredsBinding

    lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        answeredsViewModel =
            ViewModelProviders.of(this).get(AnsweredsViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentAnsweredsBinding>(
            inflater, R.layout.fragment_answereds, container, false)
        binding.model = answeredsViewModel

        recycler = binding.answeredsRecycler

        var adapter = AnsweredsAdapter(context!!)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)

        answeredsViewModel.allQuestions.observe(this, Observer { questions ->
            questions?.let { adapter.setQuestions(it) }
        })

        answeredsViewModel.allAnswers.observe(this, Observer { answers ->
            answers?.let { adapter.setAnswers(it) }
        })

        return binding.root
    }
}
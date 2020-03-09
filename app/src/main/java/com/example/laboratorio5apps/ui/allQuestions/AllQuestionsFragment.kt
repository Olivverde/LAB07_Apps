package com.example.laboratorio5apps.ui.allQuestions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio5apps.R
import com.example.laboratorio5apps.databinding.FragmentAllQuestionsBinding
/**
 * @author Olivverde
 * In cooperation with other students from Computer Science career:
 * @julioH
 * Class purpose: Main All Question fragment
 */
class AllQuestionsFragment : Fragment() {

    private lateinit var allQuestionsViewModel: AllQuestionsViewModel
    private lateinit var binding: FragmentAllQuestionsBinding

    lateinit var datos: ArrayList<String>
    lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allQuestionsViewModel =
            ViewModelProviders.of(this).get(AllQuestionsViewModel::class.java)

        binding = DataBindingUtil.inflate<FragmentAllQuestionsBinding>(
            inflater, R.layout.fragment_all_questions, container, false)

        binding.model = allQuestionsViewModel

        recycler = binding.recyclerview

        var adapter = AllQuestionsAdapter(context!!)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)

        allQuestionsViewModel.allQuestions.observe(this, Observer { questions ->
            questions?.let { adapter.setQuestions(it) }
        })

        return binding.root
    }
}
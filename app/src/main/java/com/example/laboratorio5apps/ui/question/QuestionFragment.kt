package com.example.laboratorio5apps.ui.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.laboratorio5apps.R
import com.example.laboratorio5apps.databinding.FragmentQuestionBinding
import com.example.laboratorio5apps.models.entities.Question
import kotlinx.android.synthetic.main.nav_header_main.view.*

class QuestionFragment : Fragment() {

    private lateinit var questionViewModel: QuestionViewModel
    private lateinit var binding: FragmentQuestionBinding


    private lateinit var questions: List<Question>
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel::class.java)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_question, container, false
        )
        binding.model = questionViewModel
        //
        questionViewModel.allQuestions.observe(this, Observer {
            questions = it
            binding.question.text = it.get(count).question
            setLabelNavigationTitleBar()
            setVisibleType()
        })
        //
        binding.next.setOnClickListener{view: View ->
            if (!binding.etAnswer.text.toString().equals("") || !binding.etAnswerNumber.text.toString().equals("")
                || questions.get(count).type == 3) {
                //crear encuesta
                questionViewModel.addPoll()
                //
                if (questions.get(count).type != 3) {
                    questionViewModel.addAnswerToQuestion(binding.etAnswer.text.toString(),0,0.0,questions.get(count).id)
                } else if(questions.get(count).type != 2) {
                    questionViewModel.addAnswerToQuestion("",binding.etAnswerNumber.text.toString().toInt(), 0.0,questions.get(count).id)
                } else {
                    questionViewModel.addAnswerToQuestion("",0,binding.ratingBar.rating.toString().toDouble(),questions.get(count).id)
                }
                count++
                setLabelNavigationTitleBar()
                if (count >= questions.size) {
                    view.findNavController().navigate(R.id.action_nav_question_to_nav_results)
                } else {
                    setVisibleType()
                }
                binding.etAnswer.text.clear()
                binding.etAnswerNumber.text.clear()
            } else {
                Toast.makeText(context, "Responda esta pregunta para avanzar", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    fun setVisibleType() {
        binding.question.text = questions.get(count).question
        if (questions.get(count).type == 3) {
            binding.etAnswer.visibility = View.GONE
            binding.etAnswerNumber.visibility = View.GONE
            binding.ratingBar.visibility = View.VISIBLE
        } else if (questions.get(count).type == 2) {
            binding.etAnswer.visibility = View.GONE
            binding.etAnswerNumber.visibility = View.VISIBLE
            binding.ratingBar.visibility = View.GONE
        } else {
            binding.etAnswer.visibility = View.VISIBLE
            binding.etAnswerNumber.visibility = View.GONE
            binding.ratingBar.visibility = View.GONE
        }
    }

    fun setLabelNavigationTitleBar() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.menu_question, count + 1, questions.size)
    }
}
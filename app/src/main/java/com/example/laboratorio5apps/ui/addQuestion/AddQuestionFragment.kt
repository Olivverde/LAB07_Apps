package com.example.laboratorio5apps.ui.addQuestion

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.laboratorio5apps.R
import com.example.laboratorio5apps.databinding.FragmentAddQuestionBinding
import com.example.laboratorio5apps.models.entities.Question

/**
 * @author Olivverde
 * In cooperation with other students from Computer Science career:
 * @julioH
 * Class purpose: Allow the save of questions
 */
class AddQuestionFragment : Fragment(), AdapterView.OnItemSelectedListener {

    //Attributes
    private lateinit var addQuestionViewModel: AddQuestionViewModel
    private lateinit var binding: FragmentAddQuestionBinding
    private var spinnerPos: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        addQuestionViewModel = ViewModelProviders.of(this).get(AddQuestionViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentAddQuestionBinding>(
                inflater, R.layout.fragment_add_question, container, false
        )
        binding.setLifecycleOwner(this)
        binding.model = addQuestionViewModel
        //
        val spinner: Spinner = binding.spinner
        ArrayAdapter.createFromResource(
            context,
            R.array.types_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        binding.spinner.onItemSelectedListener = this
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.save_bar -> {
            if (TextUtils.isEmpty(binding.etNewQuestion.text) || spinnerPos < 0) {
                Toast.makeText(context, "Insert the question", Toast.LENGTH_SHORT).show()
                false
            } else {
                addQuestionViewModel.insert(Question(0,binding.etNewQuestion.text.toString(),
                    spinnerPos + 1,false))
                Toast.makeText(context, "The question has been saved", Toast.LENGTH_SHORT).show()
                view?.findNavController()?.navigate(R.id.action_nav_add_question_to_nav_home)
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken, 0)
                true
            }
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
        spinnerPos = p2
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented")
    }
}
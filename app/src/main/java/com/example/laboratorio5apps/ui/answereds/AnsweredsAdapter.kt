package com.example.laboratorio5apps.ui.answereds

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio5apps.R
import com.example.laboratorio5apps.models.entities.Answer
import com.example.laboratorio5apps.models.entities.Question

class AnsweredsAdapter internal constructor(context: Context): RecyclerView.Adapter<AnsweredsAdapter.ViewHolderData>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var answers = emptyList<Answer>() // Cached copy of words
    private var questions = emptyList<Question>()

    inner class ViewHolderData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idPoll: TextView = itemView.findViewById(R.id.idPollCard)
        val idAnswer: TextView = itemView.findViewById(R.id.idAnswerCard)
        val questionAnswer: TextView = itemView.findViewById(R.id.questionAnswerCard)
        val answerText: TextView = itemView.findViewById(R.id.answerTextCard)
        val answerNumber: TextView = itemView.findViewById(R.id.answerNumberCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderData {
        val itemView = inflater.inflate(R.layout.list_answereds_recycler, parent, false)
        return ViewHolderData(itemView)
    }

    /**
     * Set questions to the dataBase
     */
    internal fun setQuestions(questions: List<Question>) {
        this.questions = questions
        notifyDataSetChanged()
    }

    /**
     * Set answers to the table
     */
    internal fun setAnswers(answers: List<Answer>) {
        this.answers = answers
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        val current = answers[position]
        holder.idPoll.text = "Survey " + current.pollId.toString()
        holder.idAnswer.text = "Answer " + current.id.toString()
        holder.questionAnswer.text = "Question: " + questions.
            find { q -> q.id == current.questionId }!!.question
        holder.answerText.text = "txt answer: " + current.answerText
        holder.answerNumber.text = "int answer: " + current.answerNumber.toString()
    }

    /**
     * IDK what i have done this
     */
    override fun getItemCount() = answers.size
}
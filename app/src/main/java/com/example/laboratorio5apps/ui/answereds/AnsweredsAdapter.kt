package com.example.laboratorio5apps.ui.answereds

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
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
        val layoutStars: LinearLayout = itemView.findViewById(R.id.layoutStars)
        val star1: ImageView = itemView.findViewById(R.id.star1)
        val star2: ImageView = itemView.findViewById(R.id.star2)
        val star3: ImageView = itemView.findViewById(R.id.star3)
        val star4: ImageView = itemView.findViewById(R.id.star4)
        val star5: ImageView = itemView.findViewById(R.id.star5)
        val sh1: ImageView = itemView.findViewById(R.id.sh1)
        val sh2: ImageView = itemView.findViewById(R.id.sh2)
        val sh3: ImageView = itemView.findViewById(R.id.sh3)
        val sh4: ImageView = itemView.findViewById(R.id.sh4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderData {
        val itemView = inflater.inflate(R.layout.list_answereds_recycler, parent, false)
        return ViewHolderData(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        val current = answers[position]
        holder.idPoll.text = "Encuesta " + current.pollId.toString()
        holder.idAnswer.text = "Respuesta " + current.id.toString()
        holder.questionAnswer.text = "pregunta: " + questions.find { q -> q.id == current.questionId }!!.question
        holder.answerText.text = "respuesta texto: " + current.answerText
        holder.answerNumber.text = "respuesta numero: " + current.answerNumber.toString()
        //
        val type = questions.find { q -> q.id == current.questionId }!!.type
        if (type == 1) {
            holder.answerText.visibility = View.VISIBLE
        } else if (type == 2) {
            holder.answerNumber.visibility = View.VISIBLE
        } else if (type == 3) {
            holder.layoutStars.visibility = View.VISIBLE
            if (current.answerRating == 1.0) {
                holder.star1.visibility = View.VISIBLE
            } else if (current.answerRating == 1.5) {
                holder.star1.visibility = View.VISIBLE
                holder.sh1.visibility = View.VISIBLE
            } else if (current.answerRating == 2.0) {
                holder.star1.visibility = View.VISIBLE
                holder.star2.visibility = View.VISIBLE
            } else if (current.answerRating == 2.5) {
                holder.star1.visibility = View.VISIBLE
                holder.star2.visibility = View.VISIBLE
                holder.sh2.visibility = View.VISIBLE
            } else if (current.answerRating == 3.0) {
                holder.star1.visibility = View.VISIBLE
                holder.star2.visibility = View.VISIBLE
                holder.star3.visibility = View.VISIBLE
            } else if (current.answerRating == 3.5) {
                holder.star1.visibility = View.VISIBLE
                holder.star2.visibility = View.VISIBLE
                holder.star3.visibility = View.VISIBLE
                holder.sh3.visibility = View.VISIBLE
            } else if (current.answerRating == 4.0) {
                holder.star1.visibility = View.VISIBLE
                holder.star2.visibility = View.VISIBLE
                holder.star3.visibility = View.VISIBLE
                holder.star4.visibility = View.VISIBLE
            } else if (current.answerRating == 4.5) {
                holder.star1.visibility = View.VISIBLE
                holder.star2.visibility = View.VISIBLE
                holder.star3.visibility = View.VISIBLE
                holder.star4.visibility = View.VISIBLE
                holder.sh4.visibility = View.VISIBLE
            } else if (current.answerRating == 5.0) {
                holder.star1.visibility = View.VISIBLE
                holder.star2.visibility = View.VISIBLE
                holder.star3.visibility = View.VISIBLE
                holder.star4.visibility = View.VISIBLE
                holder.star5.visibility = View.VISIBLE
            }
        }
    }

    internal fun setQuestions(questions: List<Question>) {
        this.questions = questions
        notifyDataSetChanged()
    }

    internal fun setAnswers(answers: List<Answer>) {
        this.answers = answers
        notifyDataSetChanged()
    }

    override fun getItemCount() = answers.size
}
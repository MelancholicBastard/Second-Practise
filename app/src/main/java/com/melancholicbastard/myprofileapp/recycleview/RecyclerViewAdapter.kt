package com.melancholicbastard.myprofileapp.recycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melancholicbastard.myprofileapp.databinding.GoalsCardBinding

class RecyclerViewAdapter(private val goals: ArrayList<Goal>) : RecyclerView.Adapter<RecyclerViewAdapter.GoalViewHolder>() {

    // создание элементов списка viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        // создание макета карточки из XML
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GoalsCardBinding.inflate(layoutInflater, parent, false)
        return GoalViewHolder(binding)
    }

    override fun getItemCount(): Int = goals.size

    // pаполнить данные в элементе списка при его отображении
    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val goal = goals[position]
        holder.onBind(goal)
    }

    inner class GoalViewHolder(private val binding: GoalsCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(goal: Goal) {
            binding.apply{
                elemant = goal // связывание данных с объектом биндинга одной карточки
                executePendingBindings()
                /*  это метод в Data Binding, который выполняет отложенные привязки немедленно.
                Этот метод используется для того, чтобы гарантировать, что привязанные данные
                обновят пользовательский интерфейс до того, как произойдут последующие операции */
            }
        }
    }
}
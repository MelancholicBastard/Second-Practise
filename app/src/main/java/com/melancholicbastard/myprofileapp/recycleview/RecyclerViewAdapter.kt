package com.melancholicbastard.myprofileapp.recycleview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melancholicbastard.myprofileapp.databinding.GoalsCardBinding

class RecyclerViewAdapter(
    private var goals: ArrayList<Goal>,
    var onItemLongClick: ((Goal) -> Boolean)? = null
) : RecyclerView.Adapter<RecyclerViewAdapter.GoalViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: ArrayList<Goal>) {
        goals = newItems
        notifyDataSetChanged() // Сообщает об изменении данных
    }


    // создание элементов списка viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        // создание макета карточки из XML
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GoalsCardBinding.inflate(layoutInflater, parent, false)
        return GoalViewHolder(binding)
    }

    override fun getItemCount(): Int = goals.size

    // заполнить данные в элементе списка при его отображении
    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val goal = goals[position]
        holder.onBind(goal)

        // вызов функции callback при долгом нажатии (может быть не передана в адаптер)
        holder.itemView.setOnLongClickListener {
            onItemLongClick?.let { it1 -> it1(goal) } == true
        }

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
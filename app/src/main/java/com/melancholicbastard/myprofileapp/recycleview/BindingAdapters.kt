package com.melancholicbastard.myprofileapp.recycleview

 import android.text.BoringLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapters {

    // Работает только с одним атрибутом
    @JvmStatic // Важно не забыть аннотацию @JvmStatic, данный метод нужно сделать как статичный
    @BindingAdapter("itemList") // аннотация DataBinding Framework для работы над "itemList"
    // функция вызывается каждый раз, когда "itemList" изменяется в XML
    fun bindRecyclerViewAdapter(recyclerView: RecyclerView, items: ArrayList<Goal>?) {
        val adapter = recyclerView.adapter
        if (adapter is RecyclerViewAdapter) {
            adapter.updateItems(items ?: arrayListOf())
        } else {
            recyclerView.adapter = RecyclerViewAdapter(items ?: arrayListOf())
        }
    }

//    // Комплексная аннотация | Не работает
//    @JvmStatic
//    @BindingAdapter("app:itemList", "app:onGoalLongClick", requireAll = true)
//    fun bindRecyclerView(
//        recyclerView: RecyclerView,
//        items: ArrayList<Goal>?,
//        onItemLongClick: ((Goal) -> Boolean)?
//    ) {
//        val adapter = recyclerView.adapter
//        if (adapter != null && adapter is RecyclerViewAdapter) {
//            adapter.updateItems(items ?: arrayListOf())
//            adapter.onItemLongClick = onItemLongClick
//        } else {
//            recyclerView.adapter = RecyclerViewAdapter(items ?: arrayListOf()).apply {
//                this.onItemLongClick = onItemLongClick
//            }
//        }
//    }

    @JvmStatic
    @BindingAdapter("app:onGoalLongClick")
    fun setOnGoalLongClickListener(
        recyclerView: RecyclerView,
        onItemLongClick: ((Goal) -> Boolean)?
    ) {
        if (recyclerView.adapter is RecyclerViewAdapter) {
            (recyclerView.adapter as RecyclerViewAdapter).apply {
                this.onItemLongClick = onItemLongClick
            }
        }
    }
}




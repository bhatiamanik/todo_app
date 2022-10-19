package com.example.todo

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_list.view.*

class TodoAdapter(
    private val todos: MutableList<Todolist>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_list,
                parent,
                false
            )

        )

    }

    fun addTodo(todo: Todolist) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDone() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()

    }

    private fun toggleStrikethrough(tvTodo_list: TextView, ischecked: Boolean) {
        if (ischecked) {
            tvTodo_list.paintFlags = tvTodo_list.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodo_list.paintFlags = tvTodo_list.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()

        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            tvTodo_List.text = curTodo.title
            cbox.isChecked = curTodo.isChecked
            toggleStrikethrough(tvTodo_List, curTodo.isChecked)
            cbox.setOnCheckedChangeListener { _, ischecked ->
                toggleStrikethrough(tvTodo_List, ischecked)
                curTodo.isChecked = !curTodo.isChecked
            }


        }

    }

    override fun getItemCount(): Int {
        return todos.size
    }
}

package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        rview.adapter = todoAdapter
        rview.layoutManager = LinearLayoutManager(this)
        bAdd.setOnClickListener {
            val todoTitle = etext.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todolist(todoTitle)
                todoAdapter.addTodo(todo)
                etext.text.clear()
            }
        }
        bDelete.setOnClickListener {
            todoAdapter.deleteDone()
        }

    }
}
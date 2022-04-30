package kz.erlan.lab6

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kz.erlan.lab6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = MainAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences = getSharedPreferences("lab8", Context.MODE_PRIVATE)
        binding.apply {
            recycler.layoutManager = LinearLayoutManager(this@MainActivity)
            recycler.adapter = adapter
            addButton.setOnClickListener { showAlert() }
            saveButton.setOnClickListener {
                saveData(sharedPreferences)
            }
        }
        loadData(sharedPreferences)
    }


    private fun showAlert(){
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Add new Task")
        val input = EditText(this)
        input.hint = "Task name:"
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            val taskTitle = input.text.toString()
            val task = Task(taskTitle, false)
            adapter.addTask(task)
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        builder.show()
    }
    private fun saveData(preferences: SharedPreferences){
        val editor = preferences.edit()
        val tasks = adapter.strTasks()
        editor.apply{
            putString("tasks", tasks)
            apply()
        }
        Toast.makeText(applicationContext, "Data saved", Toast.LENGTH_SHORT).show()
    }
    private fun loadData(preferences: SharedPreferences){
        val tasks = preferences.getString("tasks", "")
        val list = tasks?.split(" ")
        val taskList = ArrayList<Task>()
        if (list != null) {
            if (list.isNotEmpty() and (list[0] != "")) {
                for (item in list){
                    taskList.add(Task(item, false))
                }
            }
        }
        adapter.loadTasks(taskList)
    }

}
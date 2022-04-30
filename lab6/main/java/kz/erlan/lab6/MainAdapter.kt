package kz.erlan.lab6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.erlan.lab6.databinding.OneTaskBinding
import android.content.SharedPreferences


class MainAdapter
    : RecyclerView.Adapter<MainAdapter.TaskHolder> (){
    private var taskList = ArrayList<Task>()
    inner class TaskHolder(item : View) : RecyclerView.ViewHolder(item){
        private val binding = OneTaskBinding.bind(item)
        fun bind(task: Task, index: Int) = with(binding){
            taskTitle.text = task.title
            taskDone.isChecked = task.done
            deleteButton.setOnClickListener {
                removeTask(index)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_task, parent, false)
        return TaskHolder(view)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(taskList[position], position)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun addTask(task: Task){
        taskList.add(task)
        notifyDataSetChanged()
    }

    fun removeTask(index: Int){
        taskList.removeAt(index)
        notifyDataSetChanged()
    }

    fun strTasks():String{
        var tasks = ""
        var i = 0
        for (task in taskList) {
            if(i==taskList.size-1)
                tasks += task.title
            else
                tasks += task.title + " "
            i++
        }
        return tasks
    }

    fun loadTasks(tasks : ArrayList<Task>){
        this.taskList = tasks
        notifyDataSetChanged()
    }


}
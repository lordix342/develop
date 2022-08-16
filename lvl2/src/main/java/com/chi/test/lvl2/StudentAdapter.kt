package com.chi.test.lvl2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chi.test.lvl2.databinding.StudentElementBinding

class StudentAdapter(private val clickListener: ClickListener) : RecyclerView.Adapter<StudentAdapter.ItemHolder>() {

    private var listName = ArrayList<ModelStudent>()

    class ItemHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = StudentElementBinding.bind(itemView)
        fun bind(student: ModelStudent, clickListener: ClickListener) = with(binding) {
            textAge.text = student.age.toString()
            textName.text = student.name
            checkBox.isChecked = student.isStudent
            textName.setOnClickListener {
                clickListener.onClick(student)
            }
            checkBox.setOnClickListener {
                checkBox.isChecked = !checkBox.isChecked
                clickListener.onCheck(student)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.student_element, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listName.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(listName[position], clickListener)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(noteslist: ArrayList<ModelStudent>) {
        listName.clear()
        listName.addAll(noteslist)
        notifyDataSetChanged()
    }
}
interface ClickListener {
    fun onClick(student: ModelStudent)
    fun onCheck(student: ModelStudent)
}
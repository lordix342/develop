package com.chi.test.lvl2

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.chi.test.lvl2.databinding.ActivityMainBinding
import com.chi.test.lvl2.room.ModelStudent

class MainActivity : AppCompatActivity(), ClickListener {
    private lateinit var binding: ActivityMainBinding
    private val viewM: ViewModel by viewModels()
    private val adapter = StudentAdapter(this)
    private lateinit var listStudents : ArrayList<ModelStudent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewM.readDb()
        initRc()
        viewM.successInsert.observe(this) {
            if (it == true) Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.fab_create) supportFragmentManager.beginTransaction()
            .replace(R.id.container2, NewStudentFragment()).commit()
        if (item.itemId == R.id.fab_sort) showDialogSorting()
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(student: ModelStudent) {
        viewM.student(student)
        supportFragmentManager.beginTransaction().replace(R.id.container2, BlankFragment()).commit()
    }

    override fun onCheck(student: ModelStudent) {
        viewM.updateToDb(
            ModelStudent(
                null,
                student.name,
                student.age,
                !student.isStudent,
                student.date,
                student.description
            )
        )
    }

    override fun onLongClick(student: ModelStudent) {
        showDialogDelete(student)
    }

    private fun initRc() {
        binding.rcView.adapter = adapter
        binding.rcView.layoutManager = LinearLayoutManager(this)
        viewM.list.observe(this) {
            if (it != null) {
                adapter.setData(it)
                listStudents = it
            }
        }
    }

    private fun showDialogSorting() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.sort_layout)
        val sortName = dialog.findViewById(R.id.sortName) as TextView
        val sortAge = dialog.findViewById(R.id.sortAge) as TextView
        val sortStudent = dialog.findViewById(R.id.sortStudent) as TextView
        val sortDescription = dialog.findViewById(R.id.sortDescription) as TextView
        var listStudentsSorted : ArrayList<ModelStudent>
        sortName.setOnClickListener { _ ->
            listStudentsSorted = listStudents.sortedWith(compareBy { it.name }).toCollection(ArrayList())
            adapter.setData(listStudentsSorted)
            dialog.dismiss()
        }
        sortAge.setOnClickListener { _ ->
            listStudentsSorted = listStudents.sortedWith(compareBy { it.age }).toCollection(ArrayList())
            adapter.setData(listStudentsSorted)
            dialog.dismiss()
        }
        sortStudent.setOnClickListener { _ ->
            listStudentsSorted = listStudents.sortedWith(compareBy { it.isStudent }).toCollection(ArrayList())
            adapter.setData(listStudentsSorted)
            dialog.dismiss()
        }
        sortDescription.setOnClickListener { _ ->
            listStudentsSorted = listStudents.sortedWith(compareBy { it.description }).toCollection(ArrayList())
            adapter.setData(listStudentsSorted)
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showDialogDelete(student: ModelStudent) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.delete_layout)
        val yesBtn = dialog.findViewById(R.id.button2) as Button
        val noBtn = dialog.findViewById(R.id.button3) as Button
        yesBtn.setOnClickListener {
            viewM.deleteStudent(student)
            dialog.dismiss()
        }
        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }
}
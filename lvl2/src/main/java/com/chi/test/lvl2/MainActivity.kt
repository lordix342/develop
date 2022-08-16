package com.chi.test.lvl2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.chi.test.lvl2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ClickListener{
    private lateinit var binding: ActivityMainBinding
    private val viewM: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewM.readDb()
        val adapter = StudentAdapter(this)
        binding.rcView.adapter = adapter
        binding.rcView.layoutManager = LinearLayoutManager(this)
        viewM.list.observe(this) {
            if (it!=null) adapter.setData(it)
        }

    }

    override fun onClick(student: ModelStudent) {
        viewM.student(student)
        supportFragmentManager.beginTransaction().replace(R.id.container2, BlankFragment()).commit()
    }

    override fun onCheck(student: ModelStudent) {
        viewM.updateToDb(ModelStudent(null,student.name,student.age, !student.isStudent))
    }
}
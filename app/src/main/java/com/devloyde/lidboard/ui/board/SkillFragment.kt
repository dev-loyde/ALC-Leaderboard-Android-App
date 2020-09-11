package com.devloyde.lidboard.ui.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devloyde.lidboard.R
import com.devloyde.lidboard.adapters.BoardAdapter
import com.devloyde.lidboard.databinding.FragmentSkillBinding
import com.devloyde.lidboard.models.LearningItem

class SkillFragment : Fragment() {

    private lateinit var binding: FragmentSkillBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_skill, container, false)
        bindViews()
        initSkillRecyclerView()
        return binding.root
    }

    private fun bindViews() {
        binding.apply {
            recyclerView = skillRecyclerView
        }
    }

    private fun initSkillRecyclerView() {
        val adapter = BoardAdapter()
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

    }

}
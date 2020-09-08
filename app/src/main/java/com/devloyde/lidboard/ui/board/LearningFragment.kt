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
import com.devloyde.lidboard.adapters.LearningAdapter
import com.devloyde.lidboard.databinding.FragmentLearningBinding
import com.devloyde.lidboard.models.LearningItem

class LearningFragment : Fragment() {

    private lateinit var binding: FragmentLearningBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_learning, container, false)
        bindViews()
        initLearningRecyclerView()
        return binding.root
    }

    private fun bindViews() {
        binding.apply {
            recyclerView = learningRecyclerView
        }
    }

    private fun initLearningRecyclerView() {
        val adapter = LearningAdapter(mockData())
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

    }

    private fun mockData(): List<LearningItem> {
        return listOf(
            LearningItem("Thad",23,"Nigeria",R.drawable.skill_iq_trimmed),
            LearningItem("Flozzy",44,"Cotonu",R.drawable.skill_iq_trimmed),
            LearningItem("Judy",200,"Uk",R.drawable.skill_iq_trimmed),
            LearningItem("Thad",23,"Nigeria",R.drawable.skill_iq_trimmed),
            LearningItem("Flozzy",44,"Cotonu",R.drawable.skill_iq_trimmed),
            LearningItem("Judy",200,"Uk",R.drawable.skill_iq_trimmed),
            LearningItem("Thad",23,"Nigeria",R.drawable.skill_iq_trimmed),
            LearningItem("Flozzy",44,"Cotonu",R.drawable.skill_iq_trimmed),
            LearningItem("Judy",200,"Uk",R.drawable.skill_iq_trimmed),
            LearningItem("Thad",23,"Nigeria",R.drawable.skill_iq_trimmed),
            LearningItem("Flozzy",44,"Cotonu",R.drawable.skill_iq_trimmed),
            LearningItem("Judy",200,"Uk",R.drawable.skill_iq_trimmed)
        )
    }
}
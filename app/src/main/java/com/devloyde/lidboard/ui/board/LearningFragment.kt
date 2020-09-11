package com.devloyde.lidboard.ui.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devloyde.lidboard.R
import com.devloyde.lidboard.adapters.BoardAdapter
import com.devloyde.lidboard.databinding.FragmentLearningBinding
import com.devloyde.lidboard.models.LearningItem
import com.devloyde.lidboard.viewmodels.BoardViewModel

class LearningFragment : Fragment() {

    private lateinit var binding: FragmentLearningBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: BoardViewModel
    private lateinit var adapter: BoardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BoardViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
        adapter = BoardAdapter()
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        loadData()
    }

    private fun loadData(){
        viewModel.learningBoard.observe(viewLifecycleOwner) { learningBoard ->
            adapter.addLearningHours(learningBoard)
        }
    }

}
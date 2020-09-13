package com.devloyde.lidboard.ui.board

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devloyde.lidboard.R
import com.devloyde.lidboard.adapters.BoardAdapter
import com.devloyde.lidboard.databinding.FragmentSkillBinding
import com.devloyde.lidboard.databinding.LoadingBinding
import com.devloyde.lidboard.viewmodels.BoardViewModel

class SkillFragment : Fragment() {

    private lateinit var binding: FragmentSkillBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var loadingView: LoadingBinding
    private lateinit var viewModel: BoardViewModel
    private lateinit var adapter: BoardAdapter

    private lateinit var viewInflater: LayoutInflater
    private var viewContainer: ViewGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BoardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewInflater = inflater
        viewContainer = container
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_skill, container, false)
        bindViews()
        initSkillRecyclerView()
        return binding.root
    }

    private fun bindViews() {
        binding.apply {
            recyclerView = skillRecyclerView
            loadingView = loadingViewLayout
        }
    }

    private fun initSkillRecyclerView() {
        adapter = BoardAdapter()
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        showLoading()
        loadData()
    }

    private fun loadData() {
        viewModel.skillIQBoard.observe(viewLifecycleOwner) { skillBoard ->
            adapter.addLearningHours(skillBoard)
            dismissLoading()
        }
    }

    private fun showLoading() {
        loadingView.root.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun dismissLoading() {
        loadingView.root.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }
}
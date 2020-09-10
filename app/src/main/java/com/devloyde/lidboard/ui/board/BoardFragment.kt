package com.devloyde.lidboard.ui.board

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.devloyde.lidboard.R
import com.devloyde.lidboard.adapters.BoardSectionsPagerAdapter
import com.devloyde.lidboard.databinding.FragmentBoardBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * A placeholder fragment containing a simple view.
 */
class BoardFragment : Fragment() {

    private lateinit var binding: FragmentBoardBinding
    private lateinit var boardFragmentPagerAdapter: BoardSectionsPagerAdapter
    private lateinit var boardTabLayout: TabLayout
    private lateinit var boardViewPager: ViewPager2
    private lateinit var toolbar: Toolbar
    private lateinit var submitBtn: Button
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board, container, false)
        //bind views before returning root
        bindViews()
        // set up navigation controller with toolbar
        navController = findNavController()
        toolbar.setupWithNavController(navController)

        // goto submission page
        gotoSubmissionPage()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialise data after view is created
        initBoardFragmentPager()
    }

    //bind variables to their respective view components
    private fun bindViews() {
        boardTabLayout = binding.boardTabLayout
        boardViewPager = binding.boardViewPager
        toolbar = binding.boardToolbar
        submitBtn = binding.boardSubmitBtn
    }

    // Initialize Board FragmentPager Adapter, ViewPager and Tab layout with  data
    private fun initBoardFragmentPager() {
        // initialise BoardSectionsPagerAdapter with lifecycle and fragment manager
        //childFragmentManager is used because FragmentManager might already be in use
        boardFragmentPagerAdapter =
            BoardSectionsPagerAdapter(childFragmentManager, lifecycle)
        // Adds both sections fragment to the fragment pager adapter
        addBoardSections()
        // attach adapter to viewpager
        boardViewPager.adapter = boardFragmentPagerAdapter
        // syc or attach tabs to viewpager and set tab titles accordingly
        TabLayoutMediator(boardTabLayout, boardViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Learning Leaders"
                1 -> tab.text = "Skill IQ Leaders"
            }
        }.attach()
    }

    //Add board sections fragment instances to the fragment pager adapter
    private fun addBoardSections() {
        boardFragmentPagerAdapter.addFragment(LearningFragment())
        boardFragmentPagerAdapter.addFragment(SkillFragment())
    }

    //navigate to submission page
    private fun gotoSubmissionPage() {
        submitBtn.setOnClickListener {
            navController.navigate(R.id.action_boardFragment_to_submissionFragment)
        }
    }
}
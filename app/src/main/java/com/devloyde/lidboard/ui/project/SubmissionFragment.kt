package com.devloyde.lidboard.ui.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import com.devloyde.lidboard.R
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.devloyde.lidboard.databinding.FragmentSubmissionBinding
import kotlinx.android.synthetic.main.fragment_submission.*

/**
 * A simple [Fragment] subclass.
 * Use the [SubmissionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SubmissionFragment : Fragment() {

    lateinit var binding:FragmentSubmissionBinding
    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_submission,container,false)
        navController = findNavController()
        bindViews()
        toolbar.setupWithNavController(navController)
        return binding.root
    }

    private fun bindViews() {
        binding.apply{
            toolbar = submissionToolbar
        }
    }

    private fun submitProject(){

    }

}
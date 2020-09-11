package com.devloyde.lidboard.ui.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.devloyde.lidboard.R
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.devloyde.lidboard.databinding.FragmentSubmissionBinding
import com.devloyde.lidboard.models.ProjectItem
import com.devloyde.lidboard.viewmodels.BoardViewModel
import com.devloyde.lidboard.viewmodels.ProjectViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_submission.*

/**
 * A simple [Fragment] subclass.
 * Use the [SubmissionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SubmissionFragment : Fragment() {

    lateinit var binding: FragmentSubmissionBinding
    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar
    private lateinit var viewModel: ProjectViewModel

    private lateinit var firstNameInputLayout: TextInputLayout
    private lateinit var lastNameInputLayout: TextInputLayout
    private lateinit var emailAddressInputLayout: TextInputLayout
    private lateinit var githubUrlInputLayout: TextInputLayout

    private var firstName: String? = null
    private var lastName: String? = null
    private var emailAddress: String? = null
    private var githubUrl: String? = null

    private lateinit var submitBtn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_submission, container, false)
        navController = findNavController()
        bindViews()
        toolbar.setupWithNavController(navController)

        submitBtn.setOnClickListener {
            submitProject()
        }
        return binding.root
    }

    private fun bindViews() {
        binding.apply {
            toolbar = submissionToolbar
            submitBtn = projectSubmitBtn
            firstNameInputLayout = firstNameInput
            lastNameInputLayout = lastNameInput
            emailAddressInputLayout = emailAddressInput
            githubUrlInputLayout = githubUrlInput
        }
    }

    private fun submitProject() {
        binding.apply {
            firstName = firstNameInput.editText?.text.toString()
            lastName = lastNameInput.editText?.text.toString()
            emailAddress = emailAddressInput.editText?.text.toString()
            githubUrl = githubUrlInput.editText?.text.toString()
        }
        if (firstName == "" || firstName == null) {
            firstNameInputLayout.isErrorEnabled = true
            firstNameInputLayout.error = "This field is required"
        } else {
            firstNameInputLayout.isErrorEnabled = false
        }
        if (lastName == "" || lastName == null) {
            lastNameInputLayout.isErrorEnabled = true
            lastNameInputLayout.error = "This field is required"
        } else {
            lastNameInputLayout.isErrorEnabled = false
        }
        if(emailAddress == "" || emailAddress == null) {
            emailAddressInputLayout.isErrorEnabled = true
            emailAddressInputLayout.error = "This field is required"
        }else {
            emailAddressInputLayout.isErrorEnabled = false
        }
        if(githubUrl == "" || githubUrl == null) {
            githubUrlInputLayout.isErrorEnabled = true
            githubUrlInputLayout.error = "This field is required"
        }else {
            githubUrlInputLayout.isErrorEnabled = false
        }
    }

}
package com.devloyde.lidboard.ui.project

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.os.HandlerCompat.postDelayed
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavController
import com.devloyde.lidboard.R
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.devloyde.lidboard.databinding.FragmentSubmissionBinding
import com.devloyde.lidboard.databinding.SubmissionConfirmationBinding
import com.devloyde.lidboard.databinding.SubmissionFailureBinding
import com.devloyde.lidboard.databinding.SubmissionSuccessBinding
import com.devloyde.lidboard.models.ProjectItem
import com.devloyde.lidboard.viewmodels.BoardViewModel
import com.devloyde.lidboard.viewmodels.ProjectViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_submission.*

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
    private lateinit var viewInflater:LayoutInflater
    private var viewContainer: ViewGroup? = null

    private lateinit var submitBtn: MaterialButton
    private lateinit var confirmationDialogBinding: SubmissionConfirmationBinding
    private lateinit var successDialogBinding: SubmissionSuccessBinding
    private lateinit var failureDialogBinding: SubmissionFailureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewInflater = inflater
        viewContainer = container
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_submission, container, false)
        navController = findNavController()
        bindViews()
        toolbar.setupWithNavController(navController)

        submitBtn.setOnClickListener {
            checkValidations()
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

    private fun checkValidations() {
        binding.apply {
            firstName = firstNameInput.editText?.text.toString()
            lastName = lastNameInput.editText?.text.toString()
            emailAddress = emailAddressInput.editText?.text.toString()
            githubUrl = githubUrlInput.editText?.text.toString()
        }
        var validationErr = false

        if (firstName == "" || firstName == null) {
            firstNameInputLayout.isErrorEnabled = true
            firstNameInputLayout.error = "This field is required"
            validationErr = true
        } else {
            firstNameInputLayout.isErrorEnabled = false
        }
        if (lastName == "" || lastName == null) {
            lastNameInputLayout.isErrorEnabled = true
            lastNameInputLayout.error = "This field is required"
            validationErr = true
        } else {
            lastNameInputLayout.isErrorEnabled = false
        }
        if(emailAddress == "" || emailAddress == null) {
            emailAddressInputLayout.isErrorEnabled = true
            emailAddressInputLayout.error = "This field is required"
            validationErr = true
        }else {
            emailAddressInputLayout.isErrorEnabled = false
        }
        if(githubUrl == "" || githubUrl == null) {
            githubUrlInputLayout.isErrorEnabled = true
            githubUrlInputLayout.error = "This field is required"
            validationErr = true
        }else {
            githubUrlInputLayout.isErrorEnabled = false
        }

        if(!validationErr){
            submitProject(
                ProjectItem(
                emailAddress!!,
                firstName!!,
                lastName!!,
                githubUrl!!
            )
            )
        }
    }

    private fun submitProject(project: ProjectItem){
        confirmationDialogBinding =
            SubmissionConfirmationBinding.inflate(viewInflater, viewContainer, false)
        val dialog =
            MaterialAlertDialogBuilder(requireContext()).setView(confirmationDialogBinding.root)
                .setCancelable(false)
                .show()
        confirmationDialogBinding.cancel.setOnClickListener {
            dismissDialog(dialog)
        }
        confirmationDialogBinding.boardSubmitBtn.setOnClickListener {
            makeSubmission(project)
            dismissDialog(dialog)
        }


    }

    private fun makeSubmission(project: ProjectItem){
        viewModel.submitProject(project).observe(viewLifecycleOwner){ success ->
            when(success){
                true -> {
                    successDialogBinding =
                        SubmissionSuccessBinding.inflate(viewInflater, viewContainer, false)
                    val dialog =
                        MaterialAlertDialogBuilder(requireContext()).setView(successDialogBinding.root)
                            .setCancelable(true)
                            .show()
                    Handler(Looper.getMainLooper()).postDelayed({
                       dialog.dismiss()
                    }, 3000)
                }
                false -> {
                    failureDialogBinding =
                        SubmissionFailureBinding.inflate(viewInflater, viewContainer, false)
                    val dialog =
                        MaterialAlertDialogBuilder(requireContext()).setView(failureDialogBinding.root)
                            .setCancelable(true)
                            .show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        dialog.dismiss()
                    }, 3000)
                }
            }
        }
    }

    private fun dismissDialog(dialog: AlertDialog) {
        dialog.dismiss()
    }


}
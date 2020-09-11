package com.devloyde.lidboard.respositories

import android.util.Log
import android.view.LayoutInflater
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import com.devloyde.lidboard.databinding.SubmissionConfirmationBinding
import com.devloyde.lidboard.databinding.SubmissionFailureBinding
import com.devloyde.lidboard.databinding.SubmissionSuccessBinding
import com.devloyde.lidboard.models.LearningItem
import com.devloyde.lidboard.models.ProjectItem
import com.devloyde.lidboard.networking.NetworkServiceBuilder
import com.devloyde.lidboard.networking.ProjectEndpoints
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService


class ProjectRepository(
    private val executors: ExecutorService
) {

    // Retrofit request builder service to all news endpoints
    private val request =
        NetworkServiceBuilder.buildProjectService(ProjectEndpoints::class.java)

    val success: MutableLiveData<Boolean> = MutableLiveData()
    val tag = "PROJECT_REPOSITORY - "

    fun submitProject(fields: ProjectItem): MutableLiveData<Boolean> {
        executors.execute {
            val call = request.submitProject(
                fields.emailAddress,
                fields.firstName,
                fields.lastName,
                fields.linkToProject
            )

            call.enqueue(object: Callback<Void>{

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d(tag,"Error submitting project ${t.message} ${t.stackTrace}")
                    success.postValue(false)
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Log.d(tag,"Success submitting project")
                        success.postValue(true)
                    }
                }

            })
        }
        return success
    }

    companion object {
        // Singleton prevents multiple instances running at the
        // same time.
        @Volatile
        private var PROJECT_REPOSITORY_INSTANCE: ProjectRepository? = null

        fun getProjectRepository(
            executor: ExecutorService
        ): ProjectRepository {
            val tempInstance = PROJECT_REPOSITORY_INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = ProjectRepository(executor)
                PROJECT_REPOSITORY_INSTANCE = instance
                return instance
            }
        }
    }
}
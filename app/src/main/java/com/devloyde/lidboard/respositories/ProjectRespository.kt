package com.devloyde.lidboard.respositories

import android.util.Log
import com.devloyde.lidboard.models.ProjectItem
import com.devloyde.lidboard.networking.NetworkServiceBuilder
import com.devloyde.lidboard.networking.ProjectEndpoints
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

    val tag = "PROJECT_REPOSITORY - "

    fun submitProject(fields: ProjectItem): Unit {
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
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Log.d(tag,"Success submitting project")
                    }
                }

            })
        }
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
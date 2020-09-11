package com.devloyde.lidboard.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.devloyde.lidboard.models.LearningItem
import com.devloyde.lidboard.models.ProjectItem
import com.devloyde.lidboard.models.SkillItem
import com.devloyde.lidboard.respositories.ProjectRepository
import java.util.concurrent.Executors

class ProjectViewModel : ViewModel() {

    private val projectRepository: ProjectRepository
    private val executor = Executors.newFixedThreadPool(3)


    init {
        projectRepository = ProjectRepository.getProjectRepository(executor)
    }

    fun submitProject(project: ProjectItem) : LiveData<Boolean> {
        return projectRepository.submitProject(project)
    }
}
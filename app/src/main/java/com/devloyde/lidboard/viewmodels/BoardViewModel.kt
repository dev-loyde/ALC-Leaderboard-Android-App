package com.devloyde.lidboard.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.devloyde.lidboard.models.LearningItem
import com.devloyde.lidboard.models.SkillItem
import com.devloyde.lidboard.respositories.BoardRepository
import java.util.concurrent.Executors

class BoardViewModel(): ViewModel() {
    private val boardRepository: BoardRepository
    private val executor = Executors.newFixedThreadPool(3)

    init{
        boardRepository = BoardRepository.getBoardRepository(executor)
    }

    var learningBoard: LiveData<List<LearningItem>> = boardRepository.getLearningHours()
    var skillIQBoard: LiveData<List<SkillItem>> = boardRepository.getSkillIQ()

}
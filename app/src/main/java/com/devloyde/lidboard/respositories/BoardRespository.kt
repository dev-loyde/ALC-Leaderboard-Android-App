package com.devloyde.lidboard.respositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devloyde.lidboard.models.LearningItem
import com.devloyde.lidboard.models.SkillItem
import com.devloyde.lidboard.networking.BoardEndpoints
import com.devloyde.lidboard.networking.NetworkServiceBuilder
import com.devloyde.lidboard.networking.NetworkServiceBuilder.BOARD_BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService


class BoardRepository(
    private val boardExecutors: ExecutorService
) {

    // Retrofit request builder service to all news endpoints
    private val request =
        NetworkServiceBuilder.buildService(BOARD_BASE_URL, BoardEndpoints::class.java)

    val tag = "BOARD_REPOSITORY - "

    fun getLearningHours(): MutableLiveData<List<LearningItem>>? {
        val learningHoursBoard: MutableLiveData<List<LearningItem>>? = null
        boardExecutors.execute {
            Log.d(tag, "checking db for recommended news")
            val call = request.getLearningHours()
            call.enqueue(object : Callback<List<LearningItem>> {
                override fun onFailure(call: Call<List<LearningItem>>, t: Throwable) {
                    Log.d(tag,"Error fetching learning hours board ${t.cause} ${t.stackTrace}")
                }

                override fun onResponse(call: Call<List<LearningItem>>,response: Response<List<LearningItem>>) {
                    if (response.isSuccessful) {
                        Log.d(tag,"Success fetching learning hours board ")
                        learningHoursBoard?.postValue(response.body())
                    }
                }

            })
        }
        return learningHoursBoard
    }

    fun getSkillIQ(): MutableLiveData<List<SkillItem>>? {
        val learningHoursBoard: MutableLiveData<List<SkillItem>>? = null
        boardExecutors.execute {
            Log.d(tag, "checking db for recommended news")
            val call = request.getSkillIQ()
            call.enqueue(object : Callback<List<SkillItem>> {
                override fun onFailure(call: Call<List<SkillItem>>, t: Throwable) {
                    Log.d(tag,"Error fetching skill iq board ${t.cause} ${t.stackTrace}")
                }

                override fun onResponse(call: Call<List<SkillItem>>,response: Response<List<SkillItem>>) {
                    if (response.isSuccessful) {
                        Log.d(tag,"Success fetching skill iq board board ")
                        learningHoursBoard?.postValue(response.body())
                    }
                }

            })
        }
        return learningHoursBoard
    }

}
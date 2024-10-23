package com.vinayakgupta3112.jettrivia.repisitory

import android.util.Log
import com.vinayakgupta3112.jettrivia.data.DataOrException
import com.vinayakgupta3112.jettrivia.model.QuestionItem
import com.vinayakgupta3112.jettrivia.network.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val api: QuestionApi
) {
    private val dataOrException = DataOrException<
            ArrayList<QuestionItem>,
            Boolean,
            Exception>()
    suspend fun getALlQuestions(): DataOrException<
            ArrayList<QuestionItem>,
            Boolean,
            Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions()
            if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
        } catch (exception: Exception) {
            dataOrException.e = exception
            Log.d("Exc", "getALlQuestions: ${dataOrException.e!!.localizedMessage}")
        }
        return dataOrException
    }
}
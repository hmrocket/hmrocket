package com.hmrocket.coroutines

import android.util.Log
import androidx.annotation.WorkerThread
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ViewModel : CoroutineScope  {
    override val coroutineContext: CoroutineContext
            get() = Dispatchers.IO + job

    lateinit var job: Job

    fun onClear() {
        job.cancel()
    }

    suspend fun fetchUser() {
        return GlobalScope.async(Dispatchers.IO) {
            // make network call
            // return user
        }.await()
    }

    suspend fun fetchUser2() {
        return withContext(Dispatchers.IO) {
            // make network call
            // return user
        }
    }

    fun saveUser() { //
        GlobalScope.launch(Dispatchers.IO) {
            saveInDatabase() // do on IO thread
        }
    }

    @WorkerThread
    fun saveInDatabase() {
        // save things in the database
    }


    fun fetchUserWithHandler() {
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.d("tagName", "$exception handled !")
        }

        GlobalScope.launch(Dispatchers.IO + handler) {
            saveInDatabase() // do on IO thread
        }
    }

    suspend fun fetchUserWithCatchError() {
        val deferred = GlobalScope.async(Dispatchers.IO) {
            saveInDatabase() // do on IO thread
        }

        try {
            deferred.await()
        } catch (exception: Exception) {
            Log.d("tagName", "$exception handled !")
        }
    }
}
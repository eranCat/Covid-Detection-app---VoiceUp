package com.vocaai.covid19.utils

import android.util.Log
import com.google.gson.Gson
import com.vocaai.covid19.models.Submission
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


object UrlManager {

    private const val BASE_URL = Constants.baseUrl

    fun urlRecord(id: String, name: String) =
        "$BASE_URL/submissions/$id/recordings/${name}"

    fun urlSubmissions() = "$BASE_URL/submissions"

    fun urlFeed(id: String) = "$BASE_URL/submissions/$id/feedback"

    private val JSON = "application/json; charset=utf-8".toMediaType()
    private val SOUND = "audio/wav".toMediaType()


    private var client = OkHttpClient()

    fun post(url: String, json: String, completion: Completion) {

        val request = Request.Builder()
            .url(url)
            .post(json.toRequestBody(JSON))
            .build()

        GlobalScope.launch(IO) {

            val response = client.newCall(request).execute()
            val res = response.body!!.string()

            val submission = Gson().fromJson(res, Submission::class.java)

            withContext(Main) {
                completion(Result.success(submission))
            }
        }

    }

    fun post(url: String, completion: Completion) = post(url, "", completion)

    fun get(url: String, completion: Completion) {
        val request = Request.Builder().url(url).build()

        GlobalScope.launch(IO) {
            val res = client.newCall(request).execute()
                .body!!.string()
            withContext(Main) {
                completion(Result.success(res))
            }
        }
    }

    fun upload(filePath: String, url: String, completion: Completion) {

        val sourceFile = File(filePath);

        Log.d("TAG", "$sourceFile : ${sourceFile.exists()}")

        val filename = filePath.substring(filePath.lastIndexOf("/") + 1)

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("image", filename, sourceFile.asRequestBody(SOUND))
            .addFormDataPart("result", "my_image")
            .build()

        val request = Request.Builder()
            .url(url).post(requestBody).build()

        GlobalScope.launch(IO) {
            val response = client.newCall(request).execute()
            val res = response.body!!.string()

//            TODO("handle response")

            withContext(Main) {
                completion(Result.success(res))
            }

            Log.e("TAG", "Response : $res")
        }

    }
}
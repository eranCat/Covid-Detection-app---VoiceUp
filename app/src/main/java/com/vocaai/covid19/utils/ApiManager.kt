package com.vocaai.covid19.utils

import com.google.gson.Gson
import com.vocaai.covid19.models.UserData


typealias Completion = (result: Result<Any?>) -> Unit

object APIManager {

    fun sendReport(data: UserData, completion: Completion) {
        val url = UrlManager.urlSubmissions()
        val json = Gson().toJson(data)
        UrlManager.post(url, json, completion)
    }

    fun submitRecord(submitId: String, recordName: String, completion: Completion) {
        val url = UrlManager.urlRecord(submitId, recordName)
        val map = mapOf("fileType" to "audio/wav")
        val json = Gson().toJson(map)
        UrlManager.post(url,json, completion)
    }

    fun submitFeedback(submitId: String ,completion: Completion) {
        val url = UrlManager.urlFeed(submitId)
        UrlManager.post(url, completion)
    }

    fun uploadS3(url: String, fileURL: String, name: String, completion: Completion) {
        UrlManager.upload(fileURL, url, completion)
    }


}
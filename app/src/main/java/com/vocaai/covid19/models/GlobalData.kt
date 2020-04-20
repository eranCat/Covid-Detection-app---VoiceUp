package com.vocaai.covid19.models

import com.vocaai.covid19.utils.SharedPrefsHelper


object GlobalData {

    var currentSubmissionId: String?
        set(id) = SharedPrefsHelper.save("currentSubmissionId",id)
        get() = SharedPrefsHelper.getString("currentSubmissionId")


    var agreedToTerms: Boolean
        set(didAgree)=SharedPrefsHelper.save("agreedToTerms",didAgree)
        get() = SharedPrefsHelper.getBool("agreedToTerms")


}
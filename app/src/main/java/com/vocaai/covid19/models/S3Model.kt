package com.vocaai.covid19.models

data class S3Model(
    var bucket: String?,
    var key: String?,
    var presignedS3Url: String?
)

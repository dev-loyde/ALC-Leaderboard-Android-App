package com.devloyde.lidboard.models

import retrofit2.http.Field

data class ProjectItem(
    val emailAddress: String,
    val firstName: String,
    val lastName:String,
    val linkToProject: String
)
package com.devloyde.lidboard.models

data class LearningItem(
    val name: String,
    val hours: Int,
    val country:String,
    val badgeUrl: Int
)

data class SkillItem(
    val name: String,
    val score: Int,
    val country:String,
    val badgeUrl: String
)
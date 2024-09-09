package com.example.vocabbuilder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Languages")
data class Language(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    val name:String
)

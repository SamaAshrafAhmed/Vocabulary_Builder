package com.example.vocabbuilder

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "Words", foreignKeys = [ForeignKey(entity = Category::class, parentColumns = ["id"],["catId"], ForeignKey.CASCADE, ForeignKey.CASCADE)])
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val catId:Int,
    val word:String,
    val description:String,

)

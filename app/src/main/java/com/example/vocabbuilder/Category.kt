package com.example.vocabbuilder

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Categories", foreignKeys = [ForeignKey(Language::class,["id"],["langId"], ForeignKey.CASCADE, ForeignKey.CASCADE)])
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val langId:Int,
    val catName:String
)

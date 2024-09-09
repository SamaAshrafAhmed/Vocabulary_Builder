package com.example.vocabbuilder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Language::class,Category::class, Word::class], version = 1, exportSchema = false)
abstract class VocabDB:RoomDatabase() {
   abstract fun getDao():VocabDao
   companion object{
       @Volatile
       private var instance:VocabDB?=null

       @Synchronized
       fun getInstance(contxt:Context):VocabDB{
           if(instance==null)
           {
               val inst = Room.databaseBuilder(contxt.applicationContext,VocabDB::class.java,"VocabDB")
                   .fallbackToDestructiveMigration().build()
               instance = inst
           }
           return instance!!

       }
   }
}
package com.example.vocabbuilder

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface VocabDao {
    @Insert(entity = Language::class)
    suspend fun addLanguage(language: Language)

    @Insert(entity = Category::class)
    suspend fun addCategory(category: Category)

    @Insert(entity = Word::class)
    suspend fun addWord(word: Word)

    @Update(entity = Language::class)
    suspend fun updateLanguage(language: Language)

    @Update(entity = Category::class)
    suspend fun updateCategory(category: Category)

    @Update(entity = Word::class)
    suspend fun updateWord(word: Word)

    @Delete(entity = Language::class)
    suspend fun deleteLanguage(language: Language)

    @Delete(entity = Category::class)
    suspend fun deleteCategory(category: Category)

    @Delete(entity = Word::class)
    suspend fun deleteWord(word: Word)

    @Query("Select * From Languages")
     fun getLanguages():LiveData<List<Language>>

     @Query("Select * From Categories")
     fun getCategories():LiveData<List<Category>>

     @Query("Select * From Words")
     fun getWords():LiveData<List<Word>>

    @Query("Select * From Categories where langId = :langId ")
     fun getCategoriesByLangID(langId:Int):LiveData<List<Category>>

    @Query("Select * From Words where catId = :catId")
     fun getWordsByCatID(catId:Int):LiveData<List<Word>>

}
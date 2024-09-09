package com.example.vocabbuilder

import androidx.lifecycle.LiveData

class VocabRepo (val dao: VocabDao){

      var languages : LiveData<List<Language>>
     var categories : LiveData<List<Category>> = dao.getCategories()
     var words : LiveData<List<Word>> = dao.getWords()

    init {
        languages = dao.getLanguages()
    }

    suspend fun addLanguage(language: Language) = dao.addLanguage(language)
    suspend fun addCategory(category: Category) = dao.addCategory(category)
    suspend fun addWord(word: Word) = dao.addWord(word)
    suspend fun updateLanguage(language: Language) = dao.updateLanguage(language)
    suspend fun updateCategory(category: Category) = dao.updateCategory(category)
    suspend fun updateWord(word: Word) = dao.updateWord(word)
    suspend fun deleteLanguage(language: Language) = dao.deleteLanguage(language)
    suspend fun deleteCategory(category: Category) = dao.deleteCategory(category)
    suspend fun deleteWord(word: Word) = dao.deleteWord(word)


    fun getCategories(langId:Int) {
        categories = dao.getCategoriesByLangID(langId)
    }
    fun getWords(catId:Int){
        words = dao.getWordsByCatID(catId)
    }

}
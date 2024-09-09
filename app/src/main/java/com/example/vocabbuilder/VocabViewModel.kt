package com.example.vocabbuilder

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class VocabViewModel(application: Application):AndroidViewModel(application) {
    private val repository:VocabRepo
    var languages:LiveData<List<Language>>
    var categories:LiveData<List<Category>>
    var words:LiveData<List<Word>>

    init {
        val dao = VocabDB.getInstance(application).getDao()
        repository = VocabRepo(dao)
        languages = repository.languages
        categories = repository.categories
        words = repository.words
    }

    fun addLanguage(language: Language)
    {
        viewModelScope.launch {
            repository.addLanguage(language)
        }
        languages = repository.languages
    }
    fun addCategory(category: Category){
        viewModelScope.launch {
            repository.addCategory(category)
            categories = repository.categories
        }
    }
    fun addWord(word: Word){
        viewModelScope.launch {
            repository.addWord(word)
        }
        words = repository.words
    }

    fun updateLanguage(language: Language){
        viewModelScope.launch {
            repository.updateLanguage(language)
        }
        languages = repository.languages
    }
    fun updateCategory(category: Category){
        viewModelScope.launch {
            repository.updateCategory(category)
        }
        categories = repository.categories
    }
    fun updateWord(word: Word){
        viewModelScope.launch {
            repository.updateWord(word)
        }
        words = repository.words
    }
    fun deleteLanguage(language: Language){
        viewModelScope.launch {
            repository.deleteLanguage(language)
        }
        languages = repository.languages
    }
    fun deleteCategory(category: Category){
        viewModelScope.launch {
            repository.deleteCategory(category)
        }
        categories = repository.categories
    }
    fun deleteWord(word: Word){
        viewModelScope.launch {
            repository.deleteWord(word)
        }
        words = repository.words

    }
    fun getCategoriesByLangID(langId:Int){
         repository.getCategories(langId)
        categories = repository.categories

    }
    fun getWordsByCatID(catId:Int){
        repository.getWords(catId)
        words = repository.words
        }

}
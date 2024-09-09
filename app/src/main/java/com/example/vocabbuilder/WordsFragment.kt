package com.example.vocabbuilder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vocabbuilder.databinding.AddLangCategoryLayoutBinding
import com.example.vocabbuilder.databinding.FragmentWordsBinding


class WordsFragment : Fragment() {
    lateinit var binding:FragmentWordsBinding
    var catId:Int?=null
    val viewModel :VocabViewModel by viewModels()
    lateinit var wordsList: List<Word>
    lateinit var adapter: WordsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentWordsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        catId = arguments?.getInt("cat")



     adapter =WordsAdapter(listOf(),{editWord(it)},{deleteWord(it)})
        binding.rvWords.adapter = adapter

        viewModel.getWordsByCatID(catId!!)
        viewModel.words.observe(viewLifecycleOwner, Observer {
            wordsList = viewModel.words.value ?: emptyList()
            adapter.list = wordsList
            adapter.notifyDataSetChanged()
        })
       

        binding.addWord.setOnClickListener {
            addWord()
        }



    }


    fun editWord(word:Word) {

        val inflater = LayoutInflater.from(this.requireContext())
        val bindingT = AddLangCategoryLayoutBinding.inflate(inflater)
        val editDialog = AlertDialog.Builder(this.requireContext()).create()
        editDialog.setView(bindingT.root)
        bindingT.newItemEt.setText(word.word)
        bindingT.newItemEt2.isVisible = true
        bindingT.newItemEt2.setText(word.description)

        bindingT.cancelButton.setOnClickListener {
            editDialog.dismiss()
        }
        bindingT.saveBtn.setOnClickListener {

            // edit word in database
            viewModel.updateWord(Word(word.id, word.catId, bindingT.newItemEt.text.toString(), bindingT.newItemEt2.text.toString()))
            editDialog.dismiss()
        }
        editDialog.show()

    }

    fun deleteWord(word:Word) {
        // delete word from database
        viewModel.deleteWord(word)

    }

    fun addWord(){
        val inflater = LayoutInflater.from(this.requireContext())
        val bindingT = AddLangCategoryLayoutBinding.inflate(inflater)
        val addDialogue = AlertDialog.Builder(this.requireContext()).create()
        addDialogue.setView(bindingT.root)

        bindingT.newItemEt2.isVisible = true

        bindingT.cancelButton.setOnClickListener {
            addDialogue.dismiss()
        }
        bindingT.saveBtn.setOnClickListener {

            // add word in database
            viewModel.addWord(Word(0,catId!!, bindingT.newItemEt.text.toString(), bindingT.newItemEt2.text.toString()))
            addDialogue.dismiss()
        }
        addDialogue.show()

    }




}
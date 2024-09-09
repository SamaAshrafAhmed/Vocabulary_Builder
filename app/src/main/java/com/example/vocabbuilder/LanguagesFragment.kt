package com.example.vocabbuilder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.vocabbuilder.databinding.AddLangCategoryLayoutBinding
import com.example.vocabbuilder.databinding.FragmentLanguagesBinding



class LanguagesFragment : Fragment() {
    lateinit var binding: FragmentLanguagesBinding
    lateinit var langList: List<Language>
    lateinit var  langsAdapter:LangsAdapter
     val viewModel: VocabViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLanguagesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.addLang.setOnClickListener {
            addLanguage()
        }

         langsAdapter=LangsAdapter(listOf(), {
             navigate(it)
         }, {
             deleteLanguage(it)
         }, {
             editLanguage(it)
         })
        binding.rvLangs.adapter = langsAdapter


        viewModel.languages.observe(viewLifecycleOwner, Observer {
            langList = viewModel.languages.value?: emptyList()
            langsAdapter.list = langList
            langsAdapter.notifyDataSetChanged()
            Log.d("abc", "$langList")
        })


    }

    fun navigate(language: Language) {
        findNavController().navigate(
            R.id.action_languagesFragment_to_categoriesFragment,
            bundleOf("lang" to language.id)
        )
    }



    fun editLanguage(language: Language) {

        val inflater = LayoutInflater.from(this.requireContext())
        val bindingT = AddLangCategoryLayoutBinding.inflate(inflater)
        val editDialog = AlertDialog.Builder(this.requireContext()).create()
        editDialog.setView(bindingT.root)
        bindingT.newItemEt.setText(language.name)

        bindingT.cancelButton.setOnClickListener {
            editDialog.dismiss()
        }
        bindingT.saveBtn.setOnClickListener {

            viewModel.updateLanguage(Language(language.id,bindingT.newItemEt.text.toString()))
            editDialog.dismiss()
        }
        editDialog.show()

    }

    fun deleteLanguage(language: Language) {
        // delete language from database
        viewModel.deleteLanguage(language)

    }

    fun addLanguage(){
        val inflater = LayoutInflater.from(this.requireContext())
        val bindingT = AddLangCategoryLayoutBinding.inflate(inflater)
        val addDialogue = AlertDialog.Builder(this.requireContext()).create()
        addDialogue.setView(bindingT.root)

        bindingT.cancelButton.setOnClickListener {
            addDialogue.dismiss()
        }
        bindingT.saveBtn.setOnClickListener {

           viewModel.addLanguage(Language(0,bindingT.newItemEt.text.toString()))
            addDialogue.dismiss()
        }
        addDialogue.show()

    }


}
package com.example.vocabbuilder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.example.vocabbuilder.databinding.AddLangCategoryLayoutBinding
import com.example.vocabbuilder.databinding.FragmentCategoriesBinding
import kotlin.properties.Delegates


class CategoriesFragment : Fragment() {
    lateinit var binding: FragmentCategoriesBinding
    var langId: Int? = null
    lateinit var catList: List<Category>
    val viewModel: VocabViewModel by viewModels()
    lateinit var adapter: CategAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        langId = arguments?.getInt("lang")

        adapter = CategAdapter(listOf(), {
            navigate(it)
        }, {
            editCategory(it)
        }, {
            deleteCategory(it)
        })
        binding.rvCategories.adapter = adapter

        binding.addCategory.setOnClickListener {
            addCategory()
        }
        viewModel.getCategoriesByLangID(langId!!)
        viewModel.categories.observe(viewLifecycleOwner, Observer {
            catList = viewModel.categories.value ?: emptyList()
            adapter.list = catList
            adapter.notifyDataSetChanged()

        })

    }


    fun navigate(cat: Category) {
        findNavController().navigate(
            R.id.action_categoriesFragment_to_wordsFragment,
            bundleOf("cat" to cat.id)
        )

    }

    fun editCategory(category: Category) {

        val inflater = LayoutInflater.from(this.requireContext())
        val bindingT = AddLangCategoryLayoutBinding.inflate(inflater)
        val editDialog = AlertDialog.Builder(this.requireContext()).create()
        editDialog.setView(bindingT.root)
        bindingT.newItemEt.setText(category.catName)

        bindingT.cancelButton.setOnClickListener {
            editDialog.dismiss()
        }
        bindingT.saveBtn.setOnClickListener {

            // edit category in database
            viewModel.updateCategory(
                Category(
                    category.id,
                    category.langId,
                    bindingT.newItemEt.text.toString()
                )
            )
            editDialog.dismiss()
        }
        editDialog.show()

    }

    fun deleteCategory(category: Category) {
        // delete category from database
        viewModel.deleteCategory(category)

    }

    fun addCategory() {
        val inflater = LayoutInflater.from(this.requireContext())
        val bindingT = AddLangCategoryLayoutBinding.inflate(inflater)
        val addDialogue = AlertDialog.Builder(this.requireContext()).create()
        addDialogue.setView(bindingT.root)

        bindingT.cancelButton.setOnClickListener {
            addDialogue.dismiss()
        }
        bindingT.saveBtn.setOnClickListener {

            // add category in database
            viewModel.addCategory(Category(0, langId!!, bindingT.newItemEt.text.toString()))
            addDialogue.dismiss()
        }
        addDialogue.show()

    }


}
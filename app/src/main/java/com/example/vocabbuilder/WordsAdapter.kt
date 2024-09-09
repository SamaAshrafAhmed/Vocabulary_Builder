package com.example.vocabbuilder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.vocabbuilder.databinding.WordLayoutBinding

class WordsAdapter(
    var list:List<Word>,
    val onItemEdit:(Word) ->Unit,
    val onItemDelete:(Word) ->Unit,
): RecyclerView.Adapter<WordsAdapter.viewHolder>() {
    inner class viewHolder(val binding: WordLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(word:Word){
            binding.wordName.isVisible =true
            binding.wordDesc.isVisible = false
            binding.word = word
            binding.root.setOnClickListener {
                if (binding.wordName.isVisible == true) {
                    binding.wordName.isVisible = false
                    binding.wordDesc.isVisible = true
                } else {
                    binding.wordName.isVisible = true
                    binding.wordDesc.isVisible = false
                }
            }
            binding.menuBtn.setOnClickListener{
                showMenu(it,word)
            }

        }
    }

    fun showMenu(view: View, word: Word){
        val popupMenu = PopupMenu(view.context, view,0,0,R.style.popUpMenuStyle)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener {
                menuItem -> when (menuItem.itemId){
            R.id.edit_item ->{
                onItemEdit(word)
                true
            }
            R.id.delete_item ->{
               onItemDelete(word)
                true
            }
            else -> false
        }
        }
        popupMenu.show()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsAdapter.viewHolder {
        val binding = WordLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordsAdapter.viewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
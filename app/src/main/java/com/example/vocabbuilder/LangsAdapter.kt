package com.example.vocabbuilder

import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.vocabbuilder.databinding.LanguageLayoutBinding

class LangsAdapter(
    var list:List<Language>,
    val onItemClick:(Language) ->Unit,
    val onItemDelete:(Language) ->Unit,
    val onItemEdit:(Language) -> Unit,

    ):RecyclerView.Adapter<LangsAdapter.viewHolder>() {
    inner class viewHolder(val binding:LanguageLayoutBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(lang:Language){
            binding.lang = lang
            binding.root.setOnClickListener{
                onItemClick(lang)
            }
            binding.root.setOnLongClickListener {
                showMenu(it, lang)
                true
            }
        }

    }

    fun showMenu(view:View, lang:Language){
        val popupMenu = PopupMenu(view.context, view,0,0,R.style.popUpMenuStyle)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener {
            menuItem -> when (menuItem.itemId){
                R.id.edit_item ->{
                    onItemEdit(lang)
                    true
                }
            R.id.delete_item ->{
                onItemDelete(lang)
                true
            }
            else -> false
            }
        }
        popupMenu.show()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LangsAdapter.viewHolder {
        val binding = LanguageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: LangsAdapter.viewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    override fun getItemCount(): Int {
        return  list.size
    }

}
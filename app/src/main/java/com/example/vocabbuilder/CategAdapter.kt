package com.example.vocabbuilder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.vocabbuilder.databinding.CategoryLayoutBinding

class CategAdapter(
    var list:List<Category>,
    val onItemCLick:(Category) -> Unit,
    val onItemEdit:(Category) ->Unit,
    val onItemDelete:(Category) ->Unit,
):RecyclerView.Adapter<CategAdapter.viewHolder>() {
    inner class viewHolder(val binding:CategoryLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(cat:Category){
            binding.category = cat
            binding.root.setOnClickListener{
                onItemCLick(cat)
            }
            binding.root.setOnLongClickListener {
                showMenu(it, cat)
                true
            }


        }
    }
    fun showMenu(view: View, cat:Category){
        val popupMenu = PopupMenu(view.context, view,0,0,R.style.popUpMenuStyle)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener {
                menuItem -> when (menuItem.itemId){
            R.id.edit_item ->{
                onItemEdit(cat)
                true
            }
            R.id.delete_item ->{
                onItemDelete(cat)
                true
            }
            else -> false
        }
        }
        popupMenu.show()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategAdapter.viewHolder {
       val binding = CategoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategAdapter.viewHolder, position: Int) {
       holder.bind(list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
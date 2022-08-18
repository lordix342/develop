package com.chi.test.lvl3.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chi.test.lvl3.R
import com.chi.test.lvl3.databinding.ZooElementBinding
import com.chi.test.lvl3.models.ModelZooItem

class ZooAdapter(private val context: Context, private val clickListener: ClickListener) : RecyclerView.Adapter<ZooAdapter.ItemHolder>() {

    private var listName = ArrayList<ModelZooItem>()

    class ItemHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = ZooElementBinding.bind(itemView)
        fun bind(zooItem: ModelZooItem,context: Context, clickListener: ClickListener) = with(binding) {
            Glide.with(context)
                .load("http:" + zooItem.imageLink)
                .into(binding.imageView)
            textDiet.text = zooItem.diet
            textName3.text = zooItem.name
            checkBoxFavorite.setOnClickListener {
                checkBoxFavorite.isChecked = !checkBoxFavorite.isChecked
                clickListener.onCheck(zooItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.zoo_element, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listName.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(listName[position], context, clickListener)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(zooList: ArrayList<ModelZooItem>) {
        listName.clear()
        listName.addAll(zooList)
        notifyDataSetChanged()
    }
}
interface ClickListener {
    fun onCheck(zooItem: ModelZooItem)
}
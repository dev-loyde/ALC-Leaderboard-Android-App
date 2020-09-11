package com.devloyde.lidboard.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devloyde.lidboard.R
import com.devloyde.lidboard.databinding.BoardItemBinding
import com.devloyde.lidboard.models.LearningItem
import com.squareup.picasso.Picasso

class BoardAdapter() :
    RecyclerView.Adapter<BoardAdapter.LearningViewHolder>() {

    private val items: ArrayList<LearningItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearningViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = BoardItemBinding.inflate(inflater,parent,false)
        return LearningViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addLearningHours(learningHours: List<LearningItem>){
        items.addAll(learningHours)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: LearningViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }


    inner class LearningViewHolder(private val binding: BoardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LearningItem) {
            val context = binding.learnerBadge.context
            Picasso.with(context)
                .load(item.badgeUrl)
                .into(binding.learnerBadge)
            binding.learnerName.text = item.name
            binding.learnerDetails.text = context.getString(R.string.learning_leaders_detail_text, item.hours, item.country)
            binding.executePendingBindings()
        }

    }

}
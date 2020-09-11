package com.devloyde.lidboard.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.devloyde.lidboard.R
import com.devloyde.lidboard.databinding.BoardItemBinding
import com.devloyde.lidboard.models.LearningItem
import com.devloyde.lidboard.models.SkillItem
import com.squareup.picasso.Picasso

class BoardAdapter:
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: ArrayList<Any> = ArrayList()
    private val learningHours = 0
    private val skillIq = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding
        val holder: RecyclerView.ViewHolder

        when (viewType) {
            learningHours -> {
                binding = BoardItemBinding.inflate(inflater, parent, false)
                holder = LearningViewHolder(binding)
            }
            else -> {
                binding = BoardItemBinding.inflate(inflater, parent, false)
                holder = SkillViewHolder(binding)
            }
        }
        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is LearningItem -> {
                learningHours
            }
            else -> {
                skillIq
            }
        }
    }

    fun addLearningHours(learningHours: List<Any>) {
        items.addAll(learningHours)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            learningHours -> {
                val learningHolder = holder as LearningViewHolder
                val item = items[position] as LearningItem
                learningHolder.bind(item)
            }
            else -> {
                val skillHolder = holder as SkillViewHolder
                val item = items[position] as SkillItem
                skillHolder.bind(item)
            }
        }
    }


    inner class LearningViewHolder(private val binding: BoardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LearningItem) {
            val context = binding.learnerBadge.context
            Picasso.with(context)
                .load(item.badgeUrl)
                .into(binding.learnerBadge)
            binding.learnerName.text = item.name
            binding.learnerDetails.text =
                context.getString(R.string.learning_leaders_detail_text, item.hours, item.country)
            binding.executePendingBindings()
        }

    }

    inner class SkillViewHolder(private val binding: BoardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SkillItem) {
            val context = binding.learnerBadge.context
            Picasso.with(context)
                .load(item.badgeUrl)
                .into(binding.learnerBadge)
            binding.learnerName.text = item.name
            binding.learnerDetails.text =
                context.getString(R.string.skill_iq_detail_text, item.score, item.country)
            binding.executePendingBindings()
        }

    }

}
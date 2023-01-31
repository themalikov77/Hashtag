package com.example.hashtag

import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hashtag.databinding.ItemHashTagBinding

class HashTagAdapter() :
    RecyclerView.Adapter<HashTagAdapter.HashTagViewHolder>() {
    private val list: ArrayList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HashTagViewHolder {
        return HashTagViewHolder(
            ItemHashTagBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HashTagViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun addList(data: List<String>) {
        this.list.clear()
        this.list.addAll(data)
        this.list.sort()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class HashTagViewHolder(private val binding: ItemHashTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(s: String) {
            val ss = SpannableString(s)
            val words: List<String> = ss.split(" ")
            for (word in words) {
                if (word.startsWith("#")) {
                    val clickableSpan: ClickableSpan = object : ClickableSpan() {
                        override fun onClick(textView: View) {
                        }
                    }
                    ss.setSpan(
                        clickableSpan,
                        ss.indexOf(word),
                        ss.indexOf(word) + word.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }
            binding.hashTag.text = ss
            binding.hashTag.movementMethod = LinkMovementMethod.getInstance()
        }
    }
}


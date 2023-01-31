package com.example.hashtag

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.hashtag.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val hList = arrayListOf<String>()
    private lateinit var adapter: HashTagAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = HashTagAdapter()
        binding.recycler.adapter = adapter
        initListeners()
    }

    private fun initListeners() {
        binding.btnSend.setOnClickListener {
            saveHashTag()
        }
        binding.etHashtag.addTextChangedListener {
            if (binding.etHashtag.text.isNotEmpty()) {
                binding.recycler.isVisible = binding.etHashtag.text.toString()[0] == '#'
                filter(binding.etHashtag.text.toString())
            } else {
                binding.recycler.isVisible = false
            }
        }
    }
    private fun filter(text: String) {
        val newList: ArrayList<String> = ArrayList()
        for (item in hList) {
            if (item.contains(text)) {
                newList.add(item)
            }
        }
        adapter.addList(newList)
    }

    private fun saveHashTag() {
        val words: List<String> = binding.etHashtag.text.split(" ")
        for (word in words) {
            if (word.startsWith("#")) {
                hList.add(word)
            }
        }
        binding.etHashtag.text.clear()
        adapter.addList(hList)
    }
}




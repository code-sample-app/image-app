package com.example.flickrsearch

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flickrsearch.databinding.ActivityMainBinding
import com.example.flickrsearch.model.PhotoAdapter
import com.example.flickrsearch.model.PhotoViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val photosViewModel: PhotoViewModel by viewModels()
    private val photosAdapter = PhotoAdapter()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)

            binding.button.setOnClickListener{v ->
                GlobalScope.launch { val list = photosViewModel.fetchImages(binding.search.query.toString())
                    photosViewModel.sortImages(list)}
                //Remove keyboard
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(v.windowToken, 0)
            }

            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = photosAdapter

            observeViewModel()

            val view = binding.root
            setContentView(view)
        }

    fun observeViewModel() {
            photosViewModel.photos.observe(
                this, Observer{photos ->
                    photos .let { photosAdapter.updateList(photos) }}
            )
        }
    }

package com.demo.telepassdigitalandroid.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.telepassdigitalandroid.databinding.ActivityMainBinding
import com.demo.telepassdigitalandroid.dataflow.flow.PokeListDataFlow
import com.demo.telepassdigitalandroid.network.model.Poke
import com.demo.telepassdigitalandroid.view.adapter.PokeListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: PokeListDataFlow by viewModels()
    private val adapter = PokeListAdapter(::onItemClick)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.pokeListRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.pokeListRecyclerView.adapter = adapter
        binding.pokeListRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        lifecycleScope.launch {
            viewModel.listPager?.flow?.collectLatest(adapter::submitData)
        }

    }

    private fun onItemClick(poke: Poke) {
        startActivity(Intent(this, PokeDetailActivity::class.java).apply {
            putExtra(
                PokeDetailActivity.EXTRA_URL,
                poke.url
            )
        })
    }

}
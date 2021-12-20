package com.replymb.poke.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.replymb.poke.R
import com.replymb.poke.databinding.ActivityDetailBinding
import com.replymb.poke.dataflow.flow.PokeDetailDataFlow
import com.replymb.poke.dataflow.states.PokeDetailStates
import com.replymb.poke.network.model.Arts
import com.replymb.poke.network.model.PokeDetail
import com.replymb.poke.view.adapter.ImagesAdapter
import com.replymb.poke.widget.StatsIndicator
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.uniflow.android.livedata.onStates
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class PokeDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_URL = "com.replymb.poke.view.PokeDetailActivity_EXTRA_URL"
    }

    private val viewModel: PokeDetailDataFlow by viewModels()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Poke_NoActionBar)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        onStates(viewModel) { state ->
            when (state) {
                is PokeDetailStates.LoadingPokeDetail -> showLoading()
                is PokeDetailStates.ErrorPokeDetail -> showError()
                is PokeDetailStates.PokeDetailState -> fillData(state.data)
            }
        }

        intent?.getStringExtra(EXTRA_URL)?.let { viewModel.loadDetail(it) }
    }

    @SuppressLint("SetTextI18n")
    private fun fillData(data: PokeDetail) {
        binding.pokeDetailLoadingProgressBar.hide()

        data.height?.let { binding.pokeDetailHeightTextView.text = "${it * 10} cm" }
        data.weight?.let { binding.pokeDetailWeightTextView.text = "${it / 10} kg" }

        data.sprites?.other?.artwork?.front?.let {
            Glide.with(binding.root).load(it).centerInside()
                .error(R.drawable.ic_round_error_outline_24)
                .placeholder(R.drawable.ic_round_hourglass_top_24)
                .into(binding.pokeDetailImageImageView)
        }

        binding.pokeDetailToolbar.title = data.name?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() } + " #${data.id}"

        val images = ArrayList<String>()
        addInto(data.sprites?.other?.home, images)
        addInto(data.sprites?.other?.dreamWorld, images)
        addInto(data.sprites?.other?.artwork, images)

        binding.pokeDetailImagesViewPager.adapter = ImagesAdapter(images.filterNot { it.endsWith("svg") })

        binding.pokeDetailStatsViewGroup.removeAllViews()
        data.stats?.forEach {
            val view = StatsIndicator(this, null)
            view.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                marginStart = resources.getDimensionPixelSize(R.dimen.side_margin);
                marginEnd = resources.getDimensionPixelSize(R.dimen.side_margin);
            }

            view.statValue = it.baseStat ?: 0
            view.statName = it.statName?.name?.uppercase(Locale.ROOT)
            binding.pokeDetailStatsViewGroup.addView(view)
        }

    }

    private fun addInto(arts: Arts?, images: ArrayList<String>) {
        arts?.front?.let { images.add(it) }
        arts?.female?.let { images.add(it) }
        arts?.shiny?.let { images.add(it) }
        arts?.shinyFemale?.let { images.add(it) }
    }

    private fun showError() {
        binding.pokeDetailLoadingProgressBar.hide()
        Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        binding.pokeDetailLoadingProgressBar.show()
    }

}
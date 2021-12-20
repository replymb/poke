package com.replymb.poke.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.replymb.poke.R

class StatsIndicator @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {
    private var progress: ProgressBar?
    private var label: TextView?
    var statValue: Int = 0
        set(value) {
            field = value
            progress?.progress = value
        }

    var statName: CharSequence? = null
        set(value) {
            field = value
            label?.text = value
        }


    init {
        inflate(getContext(), R.layout.stats_inticator, this)
        val array = context.obtainStyledAttributes(attrs, R.styleable.StatsIndicator, 0, 0)

        statValue = array.getInt(R.styleable.StatsIndicator_statsValue, 0)
        statName = array.getString(R.styleable.StatsIndicator_statsName)

        array.recycle()

        label = findViewById(R.id.statsIndicatorLabel)
        progress = findViewById(R.id.statsIndicatorProgress)

        label?.text = statName
        progress?.progress = statValue
    }


}
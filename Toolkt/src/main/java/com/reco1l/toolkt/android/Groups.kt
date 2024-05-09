package com.reco1l.toolkt.android

import android.widget.LinearLayout.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


var RecyclerView.orientation
    get() = (layoutManager as? LinearLayoutManager)?.orientation
    set(value) {
        if (layoutManager !is LinearLayoutManager) {
            layoutManager = LinearLayoutManager(context)
        }

        (layoutManager as? LinearLayoutManager)?.orientation = value ?: VERTICAL
    }
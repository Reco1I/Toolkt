package com.reco1l.toolkt.android

import android.widget.LinearLayout.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * The orientation of the [RecyclerView] if it's using a [LinearLayoutManager].
 *
 * If the [RecyclerView] is not using a [LinearLayoutManager] then it'll set one.
 */
var RecyclerView.orientation
    get() = (layoutManager as? LinearLayoutManager)?.orientation
    set(value) {
        if (layoutManager !is LinearLayoutManager) {
            layoutManager = LinearLayoutManager(context)
        }

        (layoutManager as? LinearLayoutManager)?.orientation = value ?: VERTICAL
    }
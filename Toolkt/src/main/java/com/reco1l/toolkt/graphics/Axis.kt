package com.reco1l.toolkt.graphics

import androidx.annotation.IntDef

@IntDef(value = [
    Axis.VERTICAL,
    Axis.HORIZONTAL,
    Axis.BOTH
])
annotation class Axis
{
    companion object
    {
        const val VERTICAL = 0
        const val HORIZONTAL = 1
        const val BOTH = 2
    }
}


@IntDef(value = [Axis.VERTICAL, Axis.HORIZONTAL])
annotation class SingleAxis
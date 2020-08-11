package com.benjaminledet.core.extension

import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf

inline fun <reified T: Any> Context.intentFor(vararg pairs: Pair<String, Any?>): Intent = Intent(this, T::class.java).apply {
    if (pairs.isNotEmpty()) putExtras(bundleOf(*pairs))
}
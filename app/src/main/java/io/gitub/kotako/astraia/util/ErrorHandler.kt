package io.gitub.kotako.astraia.util

import android.util.Log

fun defaultErrorHandler(): (Throwable) -> Unit = { t -> Log.i("Error", t.message, t)}
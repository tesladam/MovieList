package freelancer.istanbul.movielist.util.extensions

import android.util.Log


inline fun <T> tryCatch(printStackTrace: Boolean = true, action: () -> T?): T? {
    var value: T? = null
    try {
        value = action()
    } catch (t: Throwable) {
        if (printStackTrace) {
            Log.e("tryCatch", "", t)
        }
    }
    return value
}
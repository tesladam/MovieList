package freelancer.istanbul.movielist.data.remote

import android.app.Application
import androidx.annotation.StringRes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StringProvider @Inject constructor(private val application: Application) {

    fun get(@StringRes resId: Int): String{
        return application.getString(resId)
    }
}
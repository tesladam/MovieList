package freelancer.istanbul.movielist

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class MovieListApplication @Inject constructor() : Application() {

}
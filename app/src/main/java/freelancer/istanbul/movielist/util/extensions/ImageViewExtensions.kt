package freelancer.istanbul.movielist.util.extensions

import android.graphics.Bitmap
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.url(url: String, cache: Boolean = false){
    if (cache)
        Glide.with(this.context).load(url).into(this)
    else
        Glide.with(this.context).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(this)
}

fun ImageView.bitmap(bitmap: Bitmap, cache: Boolean = false){
    if (cache)
        Glide.with(this.context).load(bitmap).into(this)
    else
        Glide.with(this.context).load(bitmap).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(this)

}

fun ImageView.draw(drawable: Int, cache: Boolean = true) {
    if (cache)
        Glide.with(this.context).load(drawable).into(this)
    else
        Glide.with(this.context).load(drawable).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(this)
}

fun ImageView.visible() {
    this.visibility = VISIBLE
}

fun ImageView.gone() {
    this.visibility = GONE
}

fun ImageView.invisible() {
    this.visibility = INVISIBLE
}
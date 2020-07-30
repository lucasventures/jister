package luke.example.jister.view.util

import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageHelper {
    companion object{
        fun loadImageFromUrl(imageView: ImageView, url: String) {
            if (Picasso.get() == null) {
                Picasso.setSingletonInstance(Picasso.get())
            }

            Picasso.get()
                .load(url)
                .centerCrop()
                .fit()
                .into(imageView)
        }
    }
}
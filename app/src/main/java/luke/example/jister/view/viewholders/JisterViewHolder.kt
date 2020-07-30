package luke.example.jister.view.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import luke.example.jister.R

class JisterViewHolder(view:View) : RecyclerView.ViewHolder(view) {
    val parent = view.findViewById<RelativeLayout>(R.id.parent)
    val name = view.findViewById<TextView>(R.id.name)
    val detail = view.findViewById<TextView>(R.id.description)
    val pic = view.findViewById<ImageView>(R.id.pic)
}

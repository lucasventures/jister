package luke.example.jister.view.viewholders

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import luke.example.jister.R

class JisterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val parent: RelativeLayout = view.findViewById<RelativeLayout>(R.id.parent)
    val pic: CircleImageView = view.findViewById<CircleImageView>(R.id.pic)
    val gistName: TextView = view.findViewById<TextView>(R.id.files)
    val userName: TextView = view.findViewById<TextView>(R.id.user_name)
    val description: TextView = view.findViewById<TextView>(R.id.description)
    val files: TextView = view.findViewById<TextView>(R.id.files)
}

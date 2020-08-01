package luke.example.jister.view.adapters

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import luke.example.jister.R
import luke.example.jister.model.gist.GistDataResponseInfo
import luke.example.jister.view.activities.detail.DetailActivity
import luke.example.jister.view.fragments.DetailFragment
import luke.example.jister.view.util.ImageHelper
import luke.example.jister.view.viewholders.JisterViewHolder

class JisterRecyclerAdapter(private var gistData: GistDataResponseInfo) :
    RecyclerView.Adapter<JisterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JisterViewHolder {
        return JisterViewHolder(View.inflate(parent.context, R.layout.view_holder_jister, null))
    }

    override fun getItemCount(): Int {
        return gistData.size
    }

    override fun onBindViewHolder(holder: JisterViewHolder, position: Int) {
        val gist = gistData[position]

        holder.parent.setOnClickListener {
            val intent = Intent(holder.parent.context, DetailActivity::class.java).apply {
                putExtra(DetailFragment.ARG_ITEM_ID, gist)
            }
            holder.parent.context.startActivity(intent)
        }
        val name = "@${gist.owner.login}"
        holder.userName.text = name
        holder.gistName.text = gist.id

        if (!gist.description.isNullOrEmpty()) {
            holder.description.text = gist.description
        } else {
            holder.description.text = holder.description.context.getString(R.string.no_description)
        }

        val avatarUrl = gist.owner.avatar_url

        if (avatarUrl.isNotEmpty()) {
            ImageHelper.loadImageFromUrl(holder.pic, avatarUrl)
        }

        val files = "${gist.files.size} files"
        holder.files.text = files

    }
}
package luke.example.jister.view.adapters

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import luke.example.jister.R
import luke.example.jister.model.gist.GistDataItem
import luke.example.jister.model.gist.GistDataResponseInfo
import luke.example.jister.view.activities.detail.ItemDetailActivity
import luke.example.jister.view.activities.home.ItemListActivity
import luke.example.jister.view.fragments.ItemDetailFragment
import luke.example.jister.view.util.ImageHelper
import luke.example.jister.view.viewholders.JisterViewHolder

class JisterRecyclerAdapter(activityContext: ItemListActivity, var gistData : GistDataResponseInfo) : RecyclerView.Adapter<JisterViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            ///todo: shared preferences for device type
                var intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                    putExtra(ItemDetailFragment.ARG_ITEM_ID, "asdf")
                }
                v.context.startActivity(intent)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JisterViewHolder {
        return JisterViewHolder(View.inflate(parent.context, R.layout.view_holder_jister, null))
    }

    override fun getItemCount(): Int {
        return gistData.size
    }

    override fun onBindViewHolder(holder: JisterViewHolder, position: Int) {
        holder.name.text = gistData[position].owner.login
        holder.detail.text = gistData[position].description
        val avatarUrl  = gistData[position].owner.avatar_url

        if(avatarUrl.isNotEmpty()){
            ImageHelper.loadImageFromUrl(holder.pic, avatarUrl)
        }

    }
}
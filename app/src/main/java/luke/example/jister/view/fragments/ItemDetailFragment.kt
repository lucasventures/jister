package luke.example.jister.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import luke.example.jister.R
import luke.example.jister.model.gist.GistDataItem


class ItemDetailFragment : Fragment() {


    private var gist: GistDataItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.

                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = gist?.description
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        // Show the dummy content as text in a TextView.
        gist?.let {
            rootView.findViewById<TextView>(R.id.item_detail).text = it.description
        }

        return rootView
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }

}
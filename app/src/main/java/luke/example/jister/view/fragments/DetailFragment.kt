package luke.example.jister.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import luke.example.jister.R
import luke.example.jister.model.gist.GistDataItem
import luke.example.jister.view.activities.file.FileViewingActivity


class DetailFragment : Fragment() {

    private var gist: GistDataItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                gist = it.get(ARG_ITEM_ID) as GistDataItem
                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title =
                    "@${gist?.owner?.login}'s Gist File(s)"
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)
        gist?.let { it ->
            val fileNameArray = ArrayList<String>(it.files.size)

            it.files.iterator().forEach {
                fileNameArray.add(it.key)
            }

            val listView: ListView = rootView.findViewById(R.id.list_view) as ListView
            listView.adapter = ArrayAdapter(
                requireContext(), android.R.layout.simple_list_item_1, fileNameArray
            )

            listView.onItemClickListener = OnItemClickListener { parent, view, position, id ->
                val intent = Intent(requireContext(), FileViewingActivity::class.java).apply {
                    putExtra(
                        ARG_ITEM_ID,
                        gist?.files?.get(fileNameArray[position])
                    )
                }
                context?.startActivity(intent)
            }
        }

        return rootView
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }

}
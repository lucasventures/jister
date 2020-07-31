package luke.example.jister.view.activities.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import luke.example.jister.R
import luke.example.jister.model.gist.GistDataResponseInfo
import luke.example.jister.repository.GistRepository
import luke.example.jister.view.adapters.JisterRecyclerAdapter
import luke.example.jister.viewmodel.GistViewModel


class ItemListActivity : AppCompatActivity() {

    private var tabletMode: Boolean = false
    private lateinit var gistViewModel: GistViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        recyclerView = findViewById<RecyclerView>(R.id.recycler)
        gistViewModel = ViewModelProviders.of(this).get(GistViewModel::class.java)
        gistViewModel.setRepository(GistRepository())
        gistViewModel.gistData.observe(this, Observer {
            if (it == null) {
                Toast.makeText(this, "A server error has occurred.", Toast.LENGTH_SHORT).show()
            } else {
                updateRecyclerView(it)
            }
        })

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = null

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            tabletMode = true
        }
        gistViewModel.getLatestGistData()
    }

    private fun updateRecyclerView(data: GistDataResponseInfo) {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = JisterRecyclerAdapter(this, data)
    }
}
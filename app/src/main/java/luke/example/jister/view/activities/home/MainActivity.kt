package luke.example.jister.view.activities.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import luke.example.jister.R
import luke.example.jister.model.gist.GistDataResponseInfo
import luke.example.jister.repository.GistRepository
import luke.example.jister.view.adapters.JisterRecyclerAdapter
import luke.example.jister.viewmodel.GistViewModel


class MainActivity : AppCompatActivity() {

    private var tabletMode: Boolean = false
    private lateinit var gistViewModel: GistViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        recyclerView = findViewById(R.id.recycler)
        swipeRefreshLayout = findViewById(R.id.swiperefresh)
        swipeRefreshLayout.isRefreshing = true
        gistViewModel = ViewModelProviders.of(this).get(GistViewModel::class.java)
        gistViewModel.setRepository(GistRepository())
        gistViewModel.gistData.observe(this, Observer {
            if (swipeRefreshLayout.isRefreshing) {
                swipeRefreshLayout.isRefreshing = false
            }
            if (it == null) {
                Toast.makeText(this, "A server error has occurred.", Toast.LENGTH_SHORT).show()
            } else {
                updateRecyclerView(it)
            }
        })

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = null
        swipeRefreshLayout.setOnRefreshListener {
            gistViewModel.getLatestGistData()
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.ic_octo)
        supportActionBar?.title = "Gists, by Lucas"
        //not localizing strings
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Message Lucas?", Snackbar.LENGTH_SHORT)
                .setAction("ok") {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:+15622534980")
                    startActivity(intent)
                }.show()
        }

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            tabletMode = true
        }

        gistViewModel.getLatestGistData()
    }

    private fun updateRecyclerView(data: GistDataResponseInfo) {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = JisterRecyclerAdapter(data)
    }


    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
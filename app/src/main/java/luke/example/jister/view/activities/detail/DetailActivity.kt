package luke.example.jister.view.activities.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import luke.example.jister.R
import luke.example.jister.model.gist.GistDataItem
import luke.example.jister.view.activities.home.MainActivity
import luke.example.jister.view.fragments.DetailFragment
import luke.example.jister.view.util.ImageHelper


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(findViewById(R.id.detail_toolbar))
        val gist = intent.getSerializableExtra(DetailFragment.ARG_ITEM_ID) as GistDataItem
        gist.let {
            ImageHelper.loadImageFromUrl(findViewById(R.id.expandedImage), gist.owner.avatar_url)
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Message Lucas?", Snackbar.LENGTH_LONG)
                .setAction("ok") {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:+15622534980")
                    startActivity(intent)
                }.show()
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = DetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(
                        DetailFragment.ARG_ITEM_ID,
                        intent.getSerializableExtra(DetailFragment.ARG_ITEM_ID)
                    )
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                navigateUpTo(Intent(this, MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
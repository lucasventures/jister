package luke.example.jister.view.activities.file

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.kbiakov.codeview.CodeView
import io.github.kbiakov.codeview.adapters.Format.Default.ExtraCompact
import luke.example.jister.R
import luke.example.jister.model.gist.File
import luke.example.jister.repository.GistRepository
import luke.example.jister.view.fragments.DetailFragment
import luke.example.jister.viewmodel.GistViewModel

class FileViewingActivity : AppCompatActivity() {
    private lateinit var gistFile: File
    private lateinit var gistViewModel: GistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_viewing)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        gistViewModel = ViewModelProviders.of(this).get(GistViewModel::class.java)
        gistViewModel.setRepository(GistRepository())
        gistViewModel.gistFile.observe(this, Observer {
            if (it != null) {
                val code = it.string()
                Log.d(ContentValues.TAG, "onCreate: $code")
                val codeView = findViewById<CodeView>(R.id.code_view)
                codeView.getOptions()?.withFormat(ExtraCompact)
                codeView.setCode(code)
            } else {
                Toast.makeText(
                    this,
                    "An error occurred while fetching the file.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        intent?.let {
            if (it.getSerializableExtra(DetailFragment.ARG_ITEM_ID) != null) {
                gistFile = it.getSerializableExtra(DetailFragment.ARG_ITEM_ID) as File
                supportActionBar!!.title = gistFile.filename
                gistViewModel.getGistFile(gistFile.raw_url)
            }
        }
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
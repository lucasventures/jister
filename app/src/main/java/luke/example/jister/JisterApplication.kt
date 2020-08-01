package luke.example.jister

import android.app.Application
import io.github.kbiakov.codeview.classifier.CodeProcessor

class JisterApplication : Application() {

    companion object {
        lateinit var instance: Application
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        CodeProcessor.init(this)
    }
}
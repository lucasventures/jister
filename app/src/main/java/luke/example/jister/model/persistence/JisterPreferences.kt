package luke.example.jister.model.persistence

import android.content.SharedPreferences
import android.preference.PreferenceManager
import luke.example.jister.JisterApplication

class JisterPreferences {


    class DibiupPreferenceManager {

        companion object {

            private fun getPrefs(): SharedPreferences {
                return PreferenceManager.getDefaultSharedPreferences(JisterApplication.instance)
            }

        }
    }
}
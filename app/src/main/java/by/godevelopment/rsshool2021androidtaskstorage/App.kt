package by.godevelopment.rsshool2021androidtaskstorage

import android.app.Application
import androidx.navigation.findNavController
import by.godevelopment.rsshool2021androidtaskstorage.database.SqlBox

class App : Application() {

   override fun onCreate() {
      super.onCreate()
      SqlBox.init(this)
   }
}

// android:name=".App"
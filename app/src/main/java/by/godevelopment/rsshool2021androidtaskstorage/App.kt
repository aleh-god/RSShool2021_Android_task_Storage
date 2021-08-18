package by.godevelopment.rsshool2021androidtaskstorage

import android.app.Application
import by.godevelopment.rsshool2021androidtaskstorage.database.CatProducerWithSql
import by.godevelopment.rsshool2021androidtaskstorage.database.SqlBox
import by.godevelopment.rsshool2021androidtaskstorage.entity.Cat

class App : Application() {

   override fun onCreate() {
      super.onCreate()
      SqlBox.init(this)
   }
}

// android:name=".App"
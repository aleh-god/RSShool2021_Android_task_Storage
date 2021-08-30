package by.godevelopment.rsshool2021androidtaskstorage

import android.app.Application
import android.content.Context
import by.godevelopment.rsshool2021androidtaskstorage.database.CatRepository
import by.godevelopment.rsshool2021androidtaskstorage.database.SqlBox
import by.godevelopment.rsshool2021androidtaskstorage.database.room.CatDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // No need to cancel this scope as it'll be torn down with the process
        val applicationScope = CoroutineScope(SupervisorJob())

        // Using by lazy so the database and the repository are only created when they're needed
        // rather than when the application starts
        // TODO "Здесь написать делегат с переключением СУБД"
        val database by lazy { CatDatabase.getDatabase(this, applicationScope) }
        val repository by lazy { CatRepository(database.catDao()) }

    }

   /*
   override fun onCreate() {
      super.onCreate()
      SqlBox.init(this)
   }
    */

}

// android:name=".App"
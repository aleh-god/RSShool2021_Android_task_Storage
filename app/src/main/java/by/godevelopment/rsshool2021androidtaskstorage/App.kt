package by.godevelopment.rsshool2021androidtaskstorage

import android.app.Application
import by.godevelopment.rsshool2021androidtaskstorage.database.CatProducerWithSql

class App : Application() {

    val catProducer = CatProducerWithSql(this)
}
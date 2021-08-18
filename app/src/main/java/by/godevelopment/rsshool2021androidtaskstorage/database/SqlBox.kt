package by.godevelopment.rsshool2021androidtaskstorage.database

import android.content.Context

object SqlBox {

    lateinit var catProducerWithSql: CatProducerWithSql
        private set

    fun init(context: Context) {
        catProducerWithSql = CatProducerWithSql(context.applicationContext)
    }
}
package by.godevelopment.rsshool2021androidtaskstorage.database

import android.provider.BaseColumns

// A good way to organize a contract class is to put definitions that are global to your whole database in the root level of the class.
// Then create an inner class for each table. Each inner class enumerates the corresponding table's columns.
object ContractDB {
    // Table contents are grouped together in an anonymous object.
    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "Cat_db"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_AGE = "age"
        const val COLUMN_NAME_BREED = "breed"
    }
}
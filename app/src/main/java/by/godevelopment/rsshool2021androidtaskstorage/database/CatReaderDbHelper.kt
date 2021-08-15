package by.godevelopment.rsshool2021androidtaskstorage.database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import by.godevelopment.rsshool2021androidtaskstorage.entity.Cat

class CatReaderDbHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    private fun getCursorWithTopics(): Cursor {
        return readableDatabase.rawQuery("SELECT * FROM $ContractDB.TABLE_NAME", null)
    }

    fun getListOfCats(): List<Cat> {
        val listOfTopics = mutableListOf<Cat>()
        getCursorWithTopics().use { cursor ->
            if (cursor.moveToFirst()) {
                do {
//                    val topicName = cursor.getString(cursor.getColumnIndex(TOPIC_COLUMN))
//                    listOfTopics.add("From list: $topicName")
                } while (cursor.moveToNext())
            }
        }
        return listOfTopics
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "CatReader.db"

        // By implementing the BaseColumns interface, your inner class can inherit a primary key field called _ID that some Android classes such as CursorAdapter expect it to have.
        // It's not required, but this can help your database work harmoniously with the Android framework.
        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${ContractDB.FeedEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${ContractDB.FeedEntry.COLUMN_NAME_NAME} TEXT," +
                    "${ContractDB.FeedEntry.COLUMN_NAME_AGE} INTEGER," +
                    "${ContractDB.FeedEntry.COLUMN_NAME_BREED} TEXT)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${ContractDB.FeedEntry.TABLE_NAME}"
    }
}
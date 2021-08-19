package by.godevelopment.rsshool2021androidtaskstorage.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import android.util.Log
import by.godevelopment.rsshool2021androidtaskstorage.entity.OrderType
import by.godevelopment.rsshool2021androidtaskstorage.entity.Cat

class CatProducerWithSql(context: Context) {

    private val catReaderDbHelper = CatReaderDbHelper(context.applicationContext)
    private val db_read = catReaderDbHelper.readableDatabase
    private val db_write = catReaderDbHelper.writableDatabase


    private fun getCursorWithTopics(): Cursor {
        return db_read.rawQuery("SELECT * FROM ${ContractDB.FeedEntry.TABLE_NAME}", null)
    }

    fun getListOfCats(): List<Cat> {
        val listOfTopics = mutableListOf<Cat>()
        getCursorWithTopics().use { cursor ->
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(ContractDB.FeedEntry.COLUMN_NAME_NAME))
                    val age = cursor.getInt(cursor.getColumnIndex(ContractDB.FeedEntry.COLUMN_NAME_AGE))
                    val breed =
                        cursor.getString(cursor.getColumnIndex(ContractDB.FeedEntry.COLUMN_NAME_BREED))
                    listOfTopics.add(Cat(id, name, age, breed))
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        return ArrayList(listOfTopics)
    }

    fun getSortedListOfCats(order: OrderType): List<Cat> {
        return when (order) {
            OrderType.NAME -> getListOfCats().sortedBy { Cat -> Cat.name }
            OrderType.AGE -> getListOfCats().sortedBy { Cat -> Cat.age }
            OrderType.BREED -> getListOfCats().sortedBy { Cat -> Cat.breed }
        }
    }

    fun insertCatInDataBase(name: String, age: Int, breed: String): Boolean {
        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(ContractDB.FeedEntry.COLUMN_NAME_NAME, name)
            put(ContractDB.FeedEntry.COLUMN_NAME_AGE, age)
            put(ContractDB.FeedEntry.COLUMN_NAME_BREED, breed)
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db_write?.insert(ContractDB.FeedEntry.TABLE_NAME, null, values)

        newRowId?.let {
            Log.i("db_write", "New row $newRowId")
            return true
        } ?: return false
    }

    fun getCatFromDataBase(idCat: Int) : Cat {

        // The array of columns to return (pass null to get all)
        val projection = null
        // arrayOf(BaseColumns._ID, ContractDB.FeedEntry.COLUMN_NAME_TITLE, ContractDB.FeedEntry.COLUMN_NAME_SUBTITLE)

        // Третий и четвертый аргументы (selection и selectionArgs) объединяются для создания предложения WHERE.
        // Поскольку аргументы предоставляются отдельно от запроса выбора, они экранируются перед объединением.
        // Это делает ваши операторы выбора невосприимчивыми к SQL-инъекции.

        // The columns for the WHERE clause
        val selection = "${BaseColumns._ID} = ?"
        // The values for the WHERE clause
        val selectionArgs = arrayOf("$idCat")

        val cursor = db_read.query(
            ContractDB.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        if (!cursor.moveToFirst()) return Cat(0, "Jesus", 33, "Heaven")

        val id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
        val name =
            cursor.getString(cursor.getColumnIndex(ContractDB.FeedEntry.COLUMN_NAME_NAME))
        val age = cursor.getInt(cursor.getColumnIndex(ContractDB.FeedEntry.COLUMN_NAME_AGE))
        val breed =
            cursor.getString(cursor.getColumnIndex(ContractDB.FeedEntry.COLUMN_NAME_BREED))
        cursor.close()
        return Cat(id, name, age, breed)
    }

    fun deleteCatInDataBase(idCat: Int) : Boolean {
        // Define 'where' part of query.
        val selection = "${BaseColumns._ID} LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf("$idCat")
        // Issue SQL statement.
        val deletedRows = db_write.delete(ContractDB.FeedEntry.TABLE_NAME, selection, selectionArgs)

        if (deletedRows != 0) return true
        return false
    }

    // TODO "изменить кота на поля-атрибуты"
    fun updateCatInDataBase(id: Int, name: String, age: Int, breed: String): Boolean {
        // New value for one column
        val values = ContentValues().apply {
            put(ContractDB.FeedEntry.COLUMN_NAME_NAME, name)
            put(ContractDB.FeedEntry.COLUMN_NAME_AGE, age)
            put(ContractDB.FeedEntry.COLUMN_NAME_BREED, breed)
        }

        // Which row to update, based on the title
        val selection = "${BaseColumns._ID} LIKE ?"

        // You may include ?s in the where clause, which will be replaced by the values from whereArgs. The values will be bound as Strings.
        val selectionArgs = arrayOf("${id}")
        val updateRows = db_write.update(
            ContractDB.FeedEntry.TABLE_NAME,
            values,
            selection,
            selectionArgs)

        if (updateRows != 0) return true
        return false
    }

    fun startHelperClose() {
        catReaderDbHelper.close()
    }

}
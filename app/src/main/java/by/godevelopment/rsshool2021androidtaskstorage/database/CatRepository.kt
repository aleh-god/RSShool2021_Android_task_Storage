package by.godevelopment.rsshool2021androidtaskstorage.database

import androidx.annotation.WorkerThread
import by.godevelopment.rsshool2021androidtaskstorage.database.room.CatDao
import by.godevelopment.rsshool2021androidtaskstorage.entity.Cat
import kotlinx.coroutines.flow.Flow

class CatRepository (private val catDao: CatDao) {

    val getFlowAllOrders: Flow<List<Cat>> = catDao.getAll()

    // Обозначает, что аннотированный метод должен вызываться только в рабочем потоке.
    @WorkerThread
    suspend fun insertOrder(cat: Cat) {
        catDao.insertCat(cat)
    }

    @WorkerThread
    suspend fun updateOrder(cat: Cat) {
        catDao.updateCat(cat)
    }

    @WorkerThread
    suspend fun deleteOrder(cat: Cat) {
        catDao.deleteCat(cat)
    }

    @WorkerThread
    suspend fun deleteAll() {
        catDao.deleteAll()
    }

    //    fun getSortedListOfCats(order: OrderType): List<Cat> {
//        return when (order) {
//            OrderType.ID -> getListOfCats()
//            OrderType.NAME -> getListOfCats().sortedBy { Cat -> Cat.name.lowercase() }
//            OrderType.AGE -> getListOfCats().sortedBy { Cat -> Cat.age }
//            OrderType.BREED -> getListOfCats().sortedBy { Cat -> Cat.breed.lowercase() }
//        }
//    }

}
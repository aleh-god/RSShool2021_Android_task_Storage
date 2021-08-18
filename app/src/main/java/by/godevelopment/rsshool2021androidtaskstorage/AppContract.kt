package by.godevelopment.rsshool2021androidtaskstorage

import androidx.fragment.app.Fragment
import by.godevelopment.rsshool2021androidtaskstorage.database.CatProducerWithSql

fun Fragment.contract(): AppContract = requireActivity() as AppContract

interface AppContract {

    val catProducerWithSql: CatProducerWithSql

}
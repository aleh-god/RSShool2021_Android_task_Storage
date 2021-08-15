package by.godevelopment.rsshool2021androidtaskstorage.database

import by.godevelopment.rsshool2021androidtaskstorage.entity.Cat
import com.github.javafaker.Faker


object CatProvider {

    @JvmStatic
    val catsList: List<Cat> = (1..30).map { Cat(
      id = it,
      name = Faker.instance().cat().name(),
      age = Faker.instance().number().numberBetween(1,20),
      breed = Faker.instance().cat().breed()
    ) }

}
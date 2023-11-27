package com.storage.burgers

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BurgerEntity::class],
    version = 1
)
abstract class BurgerDatabase: RoomDatabase() {

    abstract val dao: BurgerDao
}
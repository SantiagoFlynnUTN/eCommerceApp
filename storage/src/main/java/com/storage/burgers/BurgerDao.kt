package com.storage.burgers

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface BurgerDao {

    @Upsert
    suspend fun upsertAll(beers: List<BurgerEntity>)

    @Query("SELECT * FROM burgerEntity")
    fun pagingSource(): PagingSource<Int, BurgerEntity>

    @Query("DELETE FROM burgerEntity")
    suspend fun clearAll()
}
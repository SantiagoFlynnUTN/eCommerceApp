package com.storage

import android.content.Context
import androidx.room.Room
import com.domain.HiltTestInterface
import com.storage.burgers.BurgerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideBurgerDatabase(@ApplicationContext context: Context): BurgerDatabase {
        return Room.databaseBuilder(
            context,
            BurgerDatabase::class.java,
            "burgers.db",
        ).build()
    }

    @Provides
    @Singleton
    @Named("hiltStorage")
    fun provideTest(): HiltTestInterface {
        return HiltTestImpl()
    }
}
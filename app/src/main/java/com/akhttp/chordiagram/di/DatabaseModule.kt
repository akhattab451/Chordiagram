package com.akhttp.chordiagram.di

import android.content.Context
import androidx.room.Room
import com.akhttp.chordiagram.data.Chordbase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context.applicationContext,
        Chordbase::class.java,
        "chordiagram_db"
    )
        .createFromAsset("chordiagram.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(chordbase: Chordbase) = chordbase.chordDao

}
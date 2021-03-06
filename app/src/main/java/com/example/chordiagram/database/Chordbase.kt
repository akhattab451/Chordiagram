package com.example.chordiagram.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Chord::class], version = 1, exportSchema = false)
abstract class Chordbase : RoomDatabase() {
    abstract val chordDao: ChordDao

    companion object {

        @Volatile
        private var INSTANCE: Chordbase? = null

        fun getInstance(context: Context) : Chordbase {

            return synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance =
                        Room.databaseBuilder(context.applicationContext, Chordbase::class.java, "chordiagram_db")
                            .createFromAsset("chordiagram.db")
                            .fallbackToDestructiveMigration()
                            .build()

                    INSTANCE = instance
                }
                instance
            }
        }

    }


}
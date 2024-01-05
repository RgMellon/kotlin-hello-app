package br.com.alura.helloapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import br.com.alura.helloapp.data.Contato

@Database(entities = [Contato::class], version = 1)
@TypeConverters(Conversor::class)

abstract class HelloAppDatabase : RoomDatabase() {
    abstract fun contatoDao(): ContatoDao
}
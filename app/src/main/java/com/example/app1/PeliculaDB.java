package com.example.app1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Pelicula.class}, version = 1)
abstract class PeliculaDB extends RoomDatabase {
    public abstract PeliculaDAO peliculaDAO();

}

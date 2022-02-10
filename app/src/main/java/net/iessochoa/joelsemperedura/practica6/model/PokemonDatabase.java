package net.iessochoa.joelsemperedura.practica6.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Pokemon.class}, version = 1)
//Nos transforma automaticamente las fechas a entero
@TypeConverters({TransformarFechaSQLite.class})
public abstract class PokemonDatabase extends RoomDatabase {
    //permite el acceso a los metodos CRUD
    public abstract PokemonDao pokemonDao();

    //La base de datos
    //todo punto 37
}

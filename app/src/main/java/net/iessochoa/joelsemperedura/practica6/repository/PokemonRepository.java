package net.iessochoa.joelsemperedura.practica6.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.iessochoa.joelsemperedura.practica6.model.Pokemon;
import net.iessochoa.joelsemperedura.practica6.model.PokemonDao;
import net.iessochoa.joelsemperedura.practica6.model.PokemonDatabase;

import java.util.List;

public class PokemonRepository {
    //implementamos singleton
    private static volatile PokemonRepository INSTANCE;
    //Room
    private PokemonDao mPokemonDao;

    //Network retrofit
    //pendiente...
    //singleton
    public static PokemonRepository getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (PokemonRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PokemonRepository(application);
                }
            }
        }
        return INSTANCE;
    }
    //***CONSTRUCTOR***//
    private PokemonRepository(Application application){
        //Creamos la base de datos
        PokemonDatabase db = PokemonDatabase.getDatabase(application);
        //recuperamos el DAO necesario para el CRUD de la base de datos
        mPokemonDao=db.pokemonDao();
    }
    //*****ROOM******//
    public LiveData<List<Pokemon>> getFavoritoPokemons(){
        return mPokemonDao.allPokemon();
    }
    public void insert (Pokemon pokemon){
        //administramos el hilo con el executor
        PokemonDatabase.executor.execute(()->mPokemonDao.insert(pokemon));
    }
    public void delete (Pokemon pokemon){
        //administramos el hilo con el executor
        PokemonDatabase.executor.execute(()->mPokemonDao.deleteByPokemon(pokemon));
    }

    //no utiliamos la actualizacion
}

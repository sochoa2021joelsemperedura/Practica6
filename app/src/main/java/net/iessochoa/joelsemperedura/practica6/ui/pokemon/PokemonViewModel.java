package net.iessochoa.joelsemperedura.practica6.ui.pokemon;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.iessochoa.joelsemperedura.practica6.model.Pokemon;
import net.iessochoa.joelsemperedura.practica6.repository.PokemonRepository;

import java.util.List;

public class PokemonViewModel extends AndroidViewModel {
    private PokemonRepository mRepository;
    private LiveData<List<Pokemon>> mAllPokemons;


    public PokemonViewModel(@NonNull Application application) {
        super(application);
        mRepository=PokemonRepository.getInstance(application);
        //Recuperamos el LiveData de todos los pokemons
        mAllPokemons=mRepository.getPokemons();
        // estadoRecycler=new Bundle();
    }

    //trae los pokemon de la webApi que hemos recuperado
    public LiveData<List<Pokemon>> getAllPokemons()
    {
        return mAllPokemons;
    }
    //actualiza el LiveData con los siguienesPokemons de la webApi
    public void getNextPokemon(){mRepository.getNextPokemon();}

    //Inserción y borrado que se reflejará automáticamente gracias al observador creado en la actividad
    public void insert(Pokemon pokemon){
        mRepository.insert(pokemon);
    }
    public void delete(Pokemon pokemon){mRepository.delete(pokemon);
    }

    //TODO: punto 27. esta tambien pendiente del otro viewModel en la parte 2


}
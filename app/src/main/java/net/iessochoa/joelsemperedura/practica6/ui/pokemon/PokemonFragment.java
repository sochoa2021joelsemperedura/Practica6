package net.iessochoa.joelsemperedura.practica6.ui.pokemon;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.iessochoa.joelsemperedura.practica6.R;
import net.iessochoa.joelsemperedura.practica6.databinding.FragmentPokemonBinding;
import net.iessochoa.joelsemperedura.practica6.model.Pokemon;
import net.iessochoa.joelsemperedura.practica6.ui.VerPokemonFragment;
import net.iessochoa.joelsemperedura.practica6.ui.adapters.PokemonAdapter;

import java.util.Date;
import java.util.List;

public class PokemonFragment extends Fragment {

    private PokemonViewModel pokemonViewModel;
    private FragmentPokemonBinding binding;
    private PokemonAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pokemonViewModel =
                new ViewModelProvider(this).get(PokemonViewModel.class);

        binding = FragmentPokemonBinding.inflate(inflater, container, false);
        defineDetectarFinRecycler();
        View root = binding.getRoot();

        adapter = new PokemonAdapter();
        binding.rvPokemons.setAdapter(adapter);
        binding.rvPokemons.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPokemons.setBackgroundColor(Color.BLACK);
        //Asignacion del observer al adapter//llamada al observador//
        pokemonViewModel.getAllPokemons().observe(getViewLifecycleOwner(),listaPokemon ->{
            adapter.setListaPokemon(listaPokemon);
        });



        adapter.setOnItemClickListener(new PokemonAdapter.OnItemPokemonClickListener() {
            @Override
            public void onItemPokemonClick(Pokemon pokemon) {
            //creamos bundle para pasar el pokemon al fragment ver_pokemon
                Bundle argumentosBundle=new Bundle();
                argumentosBundle.putParcelable(VerPokemonFragment.ARG_POKEMON,pokemon);
                //llamamos a la acción con el id del Navigation y el bundle
                NavHostFragment.findNavController(PokemonFragment.this)
                        .navigate(R.id.action_nav_pokemon_to_verPokemonFragment,argumentosBundle);
            }
        });


        //***Evento swiper encargado de insertar pokemons a favoritos***//
        definirEventoSwiper();

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    //****Metodo que detecta cuando llega al final de los items cargados****//
    private void defineDetectarFinRecycler(){
        binding.rvPokemons.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //de esta forma sabemos si llega al final
                if ((!recyclerView.canScrollVertically(1)) && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    Log.v("scroll", getString(R.string.stFinRecycler));
                    //binding.pbPokemons.setVisibility(View.VISIBLE); //mostramos el progress bar
                    //recuperamos los 20 siguientes pokemon eso hara que se active el observador
                    pokemonViewModel.getNextPokemon();
                }

            }
        });
    }

    private void definirEventoSwiper() {

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        //Cast del viewHolder y obtenemos el pokemon
                        PokemonAdapter.PokemonViewHolder vhPokemon=(PokemonAdapter.PokemonViewHolder) viewHolder;
                        Pokemon pokemon = vhPokemon.getPokemon(); //obtenemos el pokemon sobre el que ha actuado la accion
                        insertarPokemon(pokemon,vhPokemon.getAdapterPosition()); //llamada al metodo borror pokemon
                    }
                };
        //Item touch helper que se encargara del trabajo
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        //es asociado a nuestro recyclerview
        itemTouchHelper.attachToRecyclerView(binding.rvPokemons);
    }

    //****Inserta el pokemon en favoritos****//
    private void insertarPokemon(Pokemon pokemon, int adapterPosition) {
        pokemon.setFechaCompra(new Date()); //fecha en la   que se añadio
        pokemonViewModel.insert(pokemon);
        adapter.notifyItemChanged(adapterPosition);
    }

}
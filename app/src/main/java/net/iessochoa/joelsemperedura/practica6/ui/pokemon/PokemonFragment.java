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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.iessochoa.joelsemperedura.practica6.R;
import net.iessochoa.joelsemperedura.practica6.databinding.FragmentPokemonBinding;
import net.iessochoa.joelsemperedura.practica6.model.Pokemon;
import net.iessochoa.joelsemperedura.practica6.ui.VerPokemonFragment;
import net.iessochoa.joelsemperedura.practica6.ui.adapters.PokemonAdapter;

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


        return root;
    }

    //.
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void defineDetectarFinRecycler(){
        binding.rvPokemons.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //de esta forma sabemos si llega al final
                if ((!recyclerView.canScrollVertically(1)) && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    Log.v("scroll", "fin recyclerview");
                    // pbCargandoDatos.setVisibility(View.VISIBLE);
                    //recuperamos los 20 siguientes pokemon eso hara que se active el observador
                    pokemonViewModel.getNextPokemon();
                }
            }
        });
    }
}
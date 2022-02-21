package net.iessochoa.joelsemperedura.practica6.ui.favoritos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.iessochoa.joelsemperedura.practica6.databinding.FragmentFavoritosBinding;
import net.iessochoa.joelsemperedura.practica6.model.Pokemon;
import net.iessochoa.joelsemperedura.practica6.ui.adapters.PokemonAdapter;

public class FavoritosFragment extends Fragment {

    private FavoritosViewModel favoritosViewModel;
    private FragmentFavoritosBinding binding;
    private PokemonAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoritosViewModel =
                new ViewModelProvider(this).get(FavoritosViewModel.class);

        binding = FragmentFavoritosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        adapter = new PokemonAdapter();
        binding.rvPokemons.setAdapter(adapter);
        binding.rvPokemons.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPokemons.setBackgroundColor(Color.WHITE);
        //Asignacion del observer al adapter//llamada al observador//
        favoritosViewModel.getAllPokemons().observe(getViewLifecycleOwner(),listaPokemon ->{
            adapter.setListaPokemon(listaPokemon);
        });
        //TODO definirEventoSwiper();

        return root;
    }



}








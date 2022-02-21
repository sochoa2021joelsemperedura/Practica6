package net.iessochoa.joelsemperedura.practica6.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.iessochoa.joelsemperedura.practica6.R;
import net.iessochoa.joelsemperedura.practica6.databinding.FragmentVerPokemonBinding;
import net.iessochoa.joelsemperedura.practica6.model.Pokemon;
import net.iessochoa.joelsemperedura.practica6.utils.Utils;

public class VerPokemonFragment extends Fragment {
    public static final String ARG_POKEMON = "VerPokemonFragment.pokemon";
    private FragmentVerPokemonBinding binding;

    public VerPokemonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVerPokemonBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        //recuperamos el pokemos y lo mostramos
        Pokemon pokemon=getArguments().getParcelable(ARG_POKEMON);
        binding.tvVerNombrePokemon.setText(pokemon.getNombre().toUpperCase());
        //mostramos la imagen con Glide
        Utils.cargaImagen(binding.ivVerPokemon,pokemon.getUrlImagen());
        return root;


    }
}
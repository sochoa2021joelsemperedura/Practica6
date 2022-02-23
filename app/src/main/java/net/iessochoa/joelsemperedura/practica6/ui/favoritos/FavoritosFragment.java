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

        //***Evento encargado de borrar objetos pokemon del fragment favoritos***//
        definirEventoSwiper();

        return root;
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
                        borrarPokemon(pokemon,vhPokemon.getAdapterPosition()); //llamada al metodo borror pokemon
                    }
                };
        //Item touch helper que se encargara del trabajo
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        //es asociado a nuestro recyclerview
        itemTouchHelper.attachToRecyclerView(binding.rvPokemons);
    }

    //***Metodo Borrar Pokemon***//
    private void borrarPokemon(Pokemon pokemon, int posicion){

        //Creacion de dialogo informativo con la accion de borrar
        AlertDialog.Builder dialogo = new AlertDialog.Builder(getContext());
        dialogo.setTitle("Aviso");
        dialogo.setMessage("Desea borrar a "+pokemon.getNombre());
        //Click de cancelar
        dialogo.setNegativeButton(android.R.string.cancel, new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.notifyItemChanged(posicion);//recuperamos la posición
                    }
                });
        //Click de ok
        dialogo.setPositiveButton(android.R.string.ok, new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Borramos
                        favoritosViewModel.delete(pokemon);
                    }
                });
        dialogo.show();

    }


}








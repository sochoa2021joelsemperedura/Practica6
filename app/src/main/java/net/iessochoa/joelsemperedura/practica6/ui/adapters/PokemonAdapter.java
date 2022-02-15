package net.iessochoa.joelsemperedura.practica6.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.iessochoa.joelsemperedura.practica6.R;
import net.iessochoa.joelsemperedura.practica6.model.Pokemon;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
    private List<Pokemon> listaPokemon;
    //Metodo que se llamara cuando se necesite un nuevo viewHolder.
    @NonNull
    @Override
    public PokemonAdapter.PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon,
                        parent,false);
        return new PokemonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.PokemonViewHolder holder, int position) {

    }

    //Devuelve el numero de elementos que tiene la lista
    @Override
    public int getItemCount() {
        if (listaPokemon !=null)
            return listaPokemon.size();
        else return 0;
    }

    //cuando se modifique la base de datos, actualizamos el recyclerView
    public void setListaPokemon(List<Pokemon> pokemons){
        listaPokemon = pokemons;
        notifyDataSetChanged();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNombre;
        private ImageView ivPokemon;
        private TextView tvFecha;
        //guardamos el pokemon para ismplificar
        private Pokemon pokemon;

        //**Constructor**//
        public PokemonViewHolder(@NonNull final View itemView) {
            super(itemView);
            iniciaViews();
        }

        private void iniciaViews() {
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            ivPokemon = itemView.findViewById(R.id.ivPokemon);
        }

        //recuperar pokemon cuando lo necesitemos en el listener
        public Pokemon getPokemon(){
            return pokemon;
        }
    }

}

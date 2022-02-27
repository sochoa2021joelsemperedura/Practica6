package net.iessochoa.joelsemperedura.practica6.ui.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import net.iessochoa.joelsemperedura.practica6.R;
import net.iessochoa.joelsemperedura.practica6.model.Pokemon;
import net.iessochoa.joelsemperedura.practica6.utils.Utils;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
    private List<Pokemon> listaPokemon;
    private OnItemPokemonClickListener listenerPokemon;
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

        if (listaPokemon != null) {
            //mostramos los valores en el cardviewfinal
            Pokemon pokemon = listaPokemon.get(position);
            holder.tvNombre.setText(pokemon.getNombre().toUpperCase());
            //comprobamos si tenemos fecha por si no es de base de datos. Ya que utilizamos la misma clase
            holder.tvFecha.setText(pokemon.getFechaCompraFormatoLocal());
            if (holder.tvFecha.getText() != ""){
                holder.cvItem.setCardBackgroundColor(Color.BLACK);
                holder.ivStar.setVisibility(View.VISIBLE);
            }
            //utilizamos Glide para mostrar la imagen
            Utils.cargaImagen(holder.ivPokemon,pokemon.getUrlImagen());
            //guardamos el pokemon actual
            holder.pokemon=pokemon;
        }

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
        private CardView cvItem;
        private ImageView ivStar;

        //**Constructor**//
        public PokemonViewHolder(@NonNull final View itemView) {
            super(itemView);
            iniciaViews();
           cvItem.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (listenerPokemon != null) {
                       listenerPokemon.onItemPokemonClick(listaPokemon.get(
                               PokemonViewHolder.this.getAdapterPosition()));
                   }
               }
           });
        }

        private void iniciaViews() {
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            ivPokemon = itemView.findViewById(R.id.ivPokemon);
            cvItem = itemView.findViewById(R.id.cvItem);
            ivStar = itemView.findViewById(R.id.ivStar);
        }

        //recuperar pokemon cuando lo necesitemos en el listener
        public Pokemon getPokemon(){
            return pokemon;
        }

    }
    public interface OnItemPokemonClickListener {
        void onItemPokemonClick(Pokemon pokemon);
    }
    public void setOnItemClickListener(OnItemPokemonClickListener listener){
        this.listenerPokemon = listener;
    }

}

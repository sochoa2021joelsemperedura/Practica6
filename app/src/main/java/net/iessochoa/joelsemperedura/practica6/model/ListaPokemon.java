package net.iessochoa.joelsemperedura.practica6.model;

import static net.iessochoa.joelsemperedura.practica6.model.Pokemon.NOMBRE;
import static net.iessochoa.joelsemperedura.practica6.model.Pokemon.URL;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListaPokemon {
    @ColumnInfo(name = NOMBRE)
    @NonNull
    @SerializedName("next")
    private String next;

    @ColumnInfo(name = URL)
    @NonNull
    @SerializedName("url")
    private String url;

    @SerializedName("results")
    private ArrayList<Pokemon> results;

    public String getNext() {
        return next;
    }
    public void setNext(String next) {
        this.next = next;
    }
    public ArrayList<Pokemon> getPokemons() {
        return results;
    }
    public void setPokemons(ArrayList<Pokemon> results) {
        this.results = results;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }
}

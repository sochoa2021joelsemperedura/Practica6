package net.iessochoa.joelsemperedura.practica6.ui;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import net.iessochoa.joelsemperedura.practica6.R;

public class PreferenciasFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}
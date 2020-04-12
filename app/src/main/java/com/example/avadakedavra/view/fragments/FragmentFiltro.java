package com.example.avadakedavra.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.avadakedavra.R;
import com.example.avadakedavra.databinding.FragmentFiltroBinding;

public class FragmentFiltro extends DialogFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentFiltroBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filtro, container, false);
        return binding.getRoot();
    }

    public String[] getAllHouses() {
        return getResources().getStringArray(R.array.house_list);
    }

    public static void build(FragmentManager fragmentManager) {
        FragmentFiltro fragmentFiltro = new FragmentFiltro();
        if(!fragmentFiltro.isAdded()) {
            fragmentFiltro.setCancelable(false);
            fragmentFiltro.show(fragmentManager, "filter");
        }
    }

}

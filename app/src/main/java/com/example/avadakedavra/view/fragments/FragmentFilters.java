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
import com.example.avadakedavra.databinding.FragmentFiltersBinding;

public class FragmentFilters extends DialogFragment {

    public String[] getAllHouses() {
        return getResources().getStringArray(R.array.house_list);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentFiltersBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filters, container, false);
        return binding.getRoot();
    }

    public static void build(FragmentManager fragmentManager) {
        FragmentFilters fragmentFilters =  new FragmentFilters();
        if(!fragmentFilters.isAdded()){
            fragmentFilters.setCancelable(false);
            fragmentFilters.show(fragmentManager, "filters");
        }
    }

}

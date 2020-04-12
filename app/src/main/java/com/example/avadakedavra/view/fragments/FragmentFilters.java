package com.example.avadakedavra.view.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.avadakedavra.R;
import com.example.avadakedavra.databinding.FragmentFiltersBinding;

public class FragmentFilters extends DialogFragment {

    private Context context;

    public String[] getAllHouses() {
        return getResources().getStringArray(R.array.house_list);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentFiltersBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filters, container, false);
        View view = binding.getRoot();

        ((Spinner) view.findViewById(R.id.spinerHouses)).setAdapter(ArrayAdapter.createFromResource(context, R.array.house_list, android.R.layout.simple_spinner_dropdown_item));

        return view;
    }

    public static void build(FragmentManager fragmentManager, Context context) {
        FragmentFilters fragmentFilters =  new FragmentFilters();
        if(!fragmentFilters.isAdded()){
            fragmentFilters.context = context;
            fragmentFilters.setCancelable(false);
            fragmentFilters.show(fragmentManager, "filters");
        }
    }

}

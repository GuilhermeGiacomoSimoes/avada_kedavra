package com.example.avadakedavra.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.avadakedavra.R;
import com.example.avadakedavra.databinding.FragmentFiltersBinding;
import com.example.avadakedavra.helper.Helper;
import com.example.avadakedavra.model.models.Filters;

public class FragmentFilters extends DialogFragment {

    private Context context;
    private Filters filters;

    String houses[]  = {
            "ALL",
            "GRYFFONDOR",
            "HUFFLEPUFF",
            "RAVENCLAW",
            "SLYTHERIN"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        filters = Filters.initializeFilters(context);

        FragmentFiltersBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filters, container, false);
        View view = binding.getRoot();
        binding.setFilters(filters);

        configSpinnerHouses(view);
        configOkayButtonFilters(view);

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

    private void configSpinnerHouses(View view) {
        final Spinner spinner = view.findViewById(R.id.spinerHouses);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this.context, android.R.layout.simple_spinner_item, houses);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setSelection(getHousePosition(), true);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filters.setHouseFilter(houses[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void configOkayButtonFilters(View view){
        view.findViewById(R.id.okFilters).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getActivity() != null && getActivity().getIntent() != null){
                    getActivity().getIntent().putExtra(Helper.FILTERS_KEY, filters);
                    dismiss();
                }
            }
        });
    }

    private int getHousePosition(){
        int position = 0;

        if(filters.getHouseFilter() != null){
            for( int i=0; i<houses.length; i++ ){
                String house = houses[i];
                if(house.equalsIgnoreCase(filters.getHouseFilter())){
                    position = i;
                }
            }
        }

        return position;
    }

}

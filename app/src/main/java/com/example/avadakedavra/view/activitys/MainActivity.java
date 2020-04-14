package com.example.avadakedavra.view.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.avadakedavra.R;
import com.example.avadakedavra.databinding.ActivityMainBinding;
import com.example.avadakedavra.helper.Helper;
import com.example.avadakedavra.model.models.Character;
import com.example.avadakedavra.model.http.GetData;
import com.example.avadakedavra.model.models.Filters;
import com.example.avadakedavra.view.adapter.CharactersAdapter;
import com.example.avadakedavra.view.fragments.FragmentCharacterDetail;
import com.example.avadakedavra.view.fragments.FragmentError;
import com.example.avadakedavra.view.fragments.FragmentFilters;
import com.example.avadakedavra.viewmodel.GetDataViewModel;

import java.util.List;

import io.realm.RealmChangeListener;

import static com.example.avadakedavra.R.id.llFilters;

public class MainActivity extends AppCompatActivity {

    private List<Character> characters;
    private ListView lstCharacters;
    private Filters filters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        filters = Filters.initializeFilters(this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setFilters(filters);

        new GetData(this);

        getAllCharacters();
        configListViewCharacters();
        configLayoutFilterClick();
        configDBChangesToUpdateList();
    }

    private void getAllCharacters(){
        this.characters = GetDataViewModel.allCharacters(this);
    }

    private void configListViewCharacters() {
        lstCharacters = findViewById(R.id.lstCharacters);
        lstCharacters.setAdapter(new CharactersAdapter(this, this.characters));
        lstCharacters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentCharacterDetail.buildFragment(characters.get(position), getSupportFragmentManager());
            }
        });
    }

    private void configLayoutFilterClick() {
        findViewById(llFilters).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentFilters.build(getSupportFragmentManager(), MainActivity.this);
            }
        });
    }

    private void configDBChangesToUpdateList() {
        new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                ((BaseAdapter) lstCharacters.getAdapter()).notifyDataSetChanged();
            }
        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Bundle extras = data != null
                ? data.getExtras()
                : null;

        if (extras != null && extras.get(Helper.FILTERS_KEY) != null) {
            filters = (Filters) extras.get(Helper.FILTERS_KEY);

            filterCharacters();
        } else {
            FragmentError.build(getSupportFragmentManager(), getString(R.string.error_filtering));
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void filterCharacters() {

        if(filters != null){
            if(filters.getHouseFilter() != null) {
                if(!filters.getHouseFilter().equalsIgnoreCase("ALL")){
                    //if only students and filter by house
                    if(filters.isHogwartsStudentsOnly()){
                        this.characters = GetDataViewModel.charactersByHouseAndStudentsOnly(filters.getHouseFilter(),this);
                    }

                    //only filter by house
                    else{
                        this.characters = GetDataViewModel.charactersByHouse(filters.getHouseFilter(),this);
                    }
                }

                //get all characters
                else {
                    this.characters = GetDataViewModel.allCharacters(this);
                }

                ((BaseAdapter) lstCharacters.getAdapter()).notifyDataSetChanged();
            }

        }
    }
}

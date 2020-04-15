package com.example.avadakedavra.view.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.avadakedavra.R;
import com.example.avadakedavra.databinding.ActivityMainBinding;
import com.example.avadakedavra.model.interfaces.OnResultDialog;
import com.example.avadakedavra.model.models.Character;
import com.example.avadakedavra.model.http.GetData;
import com.example.avadakedavra.model.models.Filters;
import com.example.avadakedavra.view.adapter.CharactersAdapter;
import com.example.avadakedavra.view.fragments.FragmentCharacterDetail;
import com.example.avadakedavra.view.fragments.FragmentFilters;
import com.example.avadakedavra.viewmodel.GetDataViewModel;

import java.util.List;

import io.realm.RealmChangeListener;

import static com.example.avadakedavra.R.id.llFilters;

public class MainActivity extends AppCompatActivity implements OnResultDialog {

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
                FragmentFilters.build(getSupportFragmentManager(), MainActivity.this, filters);
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

    private void filterCharacters() {

        if(filters != null){
            if(filters.getHouseFilter() != null) {
                if(!filters.getHouseFilter().equalsIgnoreCase("ALL")){
                    //if only students and filter by house
                    if(filters.isHogwartsStudentsOnly()){
                        this.characters = GetDataViewModel.charactersByHouseAndStudentsOnly(filters.getHouseFilter(),this);
                    }

                    //only filter by house
                    else {
                        this.characters = GetDataViewModel.charactersByHouse(filters.getHouseFilter(),this);
                    }
                }

                else if (filters.isHogwartsStudentsOnly()) {
                    this.characters = GetDataViewModel.studentsCharacters(this);
                }

                //get all characters
                else {
                    this.characters = GetDataViewModel.allCharacters(this);
                }

                lstCharacters.setAdapter(new CharactersAdapter(this, this.characters));
            }

        }
    }

    @Override
    public void onDialogRespond(Object result) {
        filters = (Filters) result;
        filterCharacters();
    }
}

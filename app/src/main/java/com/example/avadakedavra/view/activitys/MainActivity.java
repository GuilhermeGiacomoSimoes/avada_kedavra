package com.example.avadakedavra.view.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.avadakedavra.R;
import com.example.avadakedavra.helper.HouseENUM;
import com.example.avadakedavra.model.models.Character;
import com.example.avadakedavra.model.http.GetData;
import com.example.avadakedavra.view.fragments.FragmentCharacterDetail;
import com.example.avadakedavra.view.fragments.FragmentFilters;
import com.example.avadakedavra.viewmodel.GetDataViewModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private HouseENUM houseFilter;
    private boolean hogwartsStudentsOnly;
    private RealmChangeListener realmChangeListener;
    private List<Character> characters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetData(this);

        configListViewCharacters();

       realmChangeListener = new RealmChangeListener() {
           @Override
           public void onChange(Object o) {
               ((ListView) findViewById(R.id.lstCharacters)).deferNotifyDataSetChanged();
           }
       };

        (findViewById(R.id.llFilters)).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentFilters.build(getSupportFragmentManager(), MainActivity.this);
            }
        });
    }

    public String getHouseFilter(){
        return getString(R.string.house) + this.houseFilter.name();
    }

    public boolean getHogwartsStudentsOnly(){
        return this.hogwartsStudentsOnly;
    }

    public void setHogwartsStudentsOnly(boolean hogwartsStudentsOnly){
        this.hogwartsStudentsOnly = hogwartsStudentsOnly;
    }

    public String getStringOnlyStudents(){
        return hogwartsStudentsOnly
                ? getString(R.string.onlyStudents) + getString(R.string.yes)
                : getString(R.string.onlyStudents) + getString(R.string.no);
    }
    private List<String> getListCharacterNames(){
        getAllCharacters();

        List<String> charactersName = new ArrayList<>();

        if(characters != null && characters.size() > 0){
            for(Character character : characters){
                charactersName.add(character.getName());
            }

            return charactersName;
        }
        else {
            return new ArrayList<>();
        }
    }

    private void getAllCharacters(){
        this.characters = GetDataViewModel.allCharacters(this);
    }

    private void configListViewCharacters() {
        ListView listView = findViewById(R.id.lstCharacters);

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getListCharacterNames()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentCharacterDetail.buildFragment(characters.get(position), getSupportFragmentManager());
            }
        });
    }
}

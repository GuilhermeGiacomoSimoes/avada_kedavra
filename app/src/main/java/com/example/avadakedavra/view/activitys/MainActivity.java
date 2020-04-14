package com.example.avadakedavra.view.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.avadakedavra.R;
import com.example.avadakedavra.helper.HouseENUM;
import com.example.avadakedavra.model.models.Character;
import com.example.avadakedavra.model.http.GetData;
import com.example.avadakedavra.view.adapter.CharactersAdapter;
import com.example.avadakedavra.view.fragments.FragmentCharacterDetail;
import com.example.avadakedavra.view.fragments.FragmentFilters;
import com.example.avadakedavra.viewmodel.GetDataViewModel;

import java.util.List;

import io.realm.RealmChangeListener;

import static com.example.avadakedavra.R.id.llFilters;

public class MainActivity extends AppCompatActivity {

    private HouseENUM houseFilter;
    private boolean hogwartsStudentsOnly;

    private RealmChangeListener realmChangeListener;
    private List<Character> characters;
    private ListView lstCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetData(this);

        lstCharacters = findViewById(R.id.lstCharacters);
        configListViewCharacters();

       realmChangeListener = new RealmChangeListener() {
           @Override
           public void onChange(Object o) {
               ((BaseAdapter) lstCharacters.getAdapter()).notifyDataSetChanged();
           }
       };

        findViewById(llFilters).setOnClickListener(new View.OnClickListener() {
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

    private void getAllCharacters(){
        this.characters = GetDataViewModel.allCharacters(this);
    }

    private void configListViewCharacters() {
        getAllCharacters();

        lstCharacters.setAdapter(new CharactersAdapter(this, this.characters));

        lstCharacters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentCharacterDetail.buildFragment(characters.get(position), getSupportFragmentManager());
            }
        });
    }
}

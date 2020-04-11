package com.example.avadakedavra.view.activitys;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.avadakedavra.R;
import com.example.avadakedavra.helper.HouseENUM;
import com.example.avadakedavra.model.models.Character;
import com.example.avadakedavra.model.http.GetData;
import com.example.avadakedavra.viewmodel.GetDataViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private HouseENUM houseFilter;
    private boolean hogwartsStudentsOnly;
    //private BaseAdapter adapter;
    private List<Character> allCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetData(this);

       ((ListView) findViewById(R.id.lstCharacters)).setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getListCharacterNames()));
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
        List<Character> characters = getAllCharacters();
        List<String> charactersName = new ArrayList<>();

        for(Character character : characters){
            charactersName.add(character.getName());
        }

        return charactersName;
    }

    private List<Character> getAllCharacters(){
        allCharacters = GetDataViewModel.allCharacters(this);
        return allCharacters;
    }
}

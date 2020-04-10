package com.example.avadakedavra.view.activitys;

import android.os.Bundle;
import android.widget.BaseAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.avadakedavra.R;
import com.example.avadakedavra.helper.HouseENUM;
import com.example.avadakedavra.model.Character;
import com.example.avadakedavra.view.adapter.CharactersAdapter;
import com.example.avadakedavra.viewmodel.GetDataViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private HouseENUM houseFilter;
    private boolean hogwartsStudentsOnly;
    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new CharactersAdapter(this, getCharacters());

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

    private List<Character> getCharacters(){
        return GetDataViewModel.allCharacters(this);
    }
}

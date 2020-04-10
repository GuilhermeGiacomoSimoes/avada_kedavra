package com.example.avadakedavra.view.activitys;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.avadakedavra.R;

public class MainActivity extends AppCompatActivity {

    private String houseFilter;
    private boolean hogwartsStudentsOnly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String getHouseFilter(){
        return getString(R.string.house) + this.houseFilter;
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

}

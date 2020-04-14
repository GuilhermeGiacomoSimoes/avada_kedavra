package com.example.avadakedavra.model.models;

import android.content.Context;
import android.view.View;

import com.example.avadakedavra.R;

import java.io.Serializable;

public class Filters implements Serializable {

    private String houseFilter = "ALL";
    private boolean hogwartsStudentsOnly = false;
    private Context context;

    public Filters(Context context) {
        this.context = context;
    }

    public String getStringOnlyStudents(){
        return hogwartsStudentsOnly
                ? context.getResources().getString(R.string.onlyStudents) + context.getResources().getString(R.string.yes)
                : context.getResources().getString(R.string.onlyStudents) + context.getResources().getString(R.string.no);
    }

    public String getStringHouseFilter() {
        return context.getResources().getString(R.string.house) + this.houseFilter;
    }

    public String getHouseFilter(){
        return houseFilter;
    }

    public boolean isHogwartsStudentsOnly() {
        return hogwartsStudentsOnly;
    }

    public void setHouseFilter(String houseFilter) {
        this.houseFilter = houseFilter;
    }

    public void changeHogwartsStudentsOnly(View view) {
        this.hogwartsStudentsOnly = !this.hogwartsStudentsOnly;
    }

}

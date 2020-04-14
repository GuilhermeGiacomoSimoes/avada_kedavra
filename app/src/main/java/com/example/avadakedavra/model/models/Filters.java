package com.example.avadakedavra.model.models;

import android.content.Context;
import android.view.View;

import com.example.avadakedavra.R;

import java.io.Serializable;

public class Filters implements Serializable {

    private String houseFilter;
    private boolean hogwartsStudentsOnly;
    private Context context;

    private Filters(Context context, String houseFilter, boolean hogwartsStudentsOnly){
        this.context = context;
        this.houseFilter = houseFilter;
        this.hogwartsStudentsOnly = hogwartsStudentsOnly;
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

    public static Filters initializeFilters(Context context){
        return new Filters(context, "ALL", false);
    }

}

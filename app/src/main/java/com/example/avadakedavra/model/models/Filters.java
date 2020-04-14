package com.example.avadakedavra.model.models;

import android.view.View;

import java.io.Serializable;

public class Filters implements Serializable {

    private String houseFilter;
    private boolean hogwartsStudentsOnly;

    public boolean isHogwartsStudentsOnly() {
        return hogwartsStudentsOnly;
    }

    public void setHogwartsStudentsOnly(boolean hogwartsStudentsOnly) {
        this.hogwartsStudentsOnly = hogwartsStudentsOnly;
    }

    public String getHouseFilter() {
        return houseFilter;
    }

    public void setHouseFilter(String houseFilter) {
        this.houseFilter = houseFilter;
    }

    public void changeHogwartsStudentsOnly(View view) {
        this.hogwartsStudentsOnly = !this.hogwartsStudentsOnly;
    }
}

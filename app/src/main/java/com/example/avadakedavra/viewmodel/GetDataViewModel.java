package com.example.avadakedavra.viewmodel;

import android.content.Context;

import com.example.avadakedavra.model.bd.GetDataModel;
import com.example.avadakedavra.model.models.Character;

import java.util.List;

import io.realm.RealmResults;

public class GetDataViewModel {
    public static RealmResults<Character> allCharacters(Context context){
        return GetDataModel.allCharacters(context);
    }

    public static List<Character> studentsCharacters(Context context){
       return GetDataModel.studentsCharacters(context);
    }

    public static List<Character> charactersByHouse(String house, Context context){
        return GetDataModel.charactersByHouse(house, context);
    }

    public static List<Character> charactersByHouseAndStudentsOnly(String house, Context context){
        return GetDataModel.charactersByHouseAndStudentsOnly(house, context);
    }

}

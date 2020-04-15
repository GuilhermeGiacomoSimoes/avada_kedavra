package com.example.avadakedavra.viewmodel;

import android.content.Context;

import com.example.avadakedavra.model.bd.SetDataModel;
import com.example.avadakedavra.model.models.Character;

import java.util.List;

public class SetDataViewModel {
    public static boolean saveCharacters(List<Character> characters, final Context context){
        return SetDataModel.saveCharacters(characters, context);
    }

    public static boolean deleteAll(Context context){
        return SetDataModel.deleteAll(context);
    }
}

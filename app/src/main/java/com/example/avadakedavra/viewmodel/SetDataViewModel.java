package com.example.avadakedavra.viewmodel;

import com.example.avadakedavra.model.models.Character;

import java.util.List;

public class SetDataViewModel {
    public static boolean saveCharacters(List<Character> characters){
        try{

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

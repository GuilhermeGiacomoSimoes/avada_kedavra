package com.example.avadakedavra.viewmodel;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.avadakedavra.model.models.Character;
import com.example.avadakedavra.view.fragments.FragmentError;

import java.util.List;

import io.realm.Realm;

public class SetDataViewModel {
    public static boolean saveCharacters(List<Character> characters){
        Realm realm = RealmConfig.getInstance();

        try{
            for (Character character : characters){
                realm.insert(character);
            }
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;

        }finally {
            realm.close();
        }
    }

    public static boolean deleteAll(Context context){
        Realm realm = RealmConfig.getInstance();

        try {
            realm.deleteAll();
            return true;

        }catch (Exception e){
            FragmentError.build(((AppCompatActivity) context).getSupportFragmentManager(), e.toString());
            e.printStackTrace();
            return false;

        }finally {
            realm.close();
        }
    }
}

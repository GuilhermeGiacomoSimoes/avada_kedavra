package com.example.avadakedavra.viewmodel;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.avadakedavra.helper.HouseENUM;
import com.example.avadakedavra.model.models.Character;
import com.example.avadakedavra.view.fragments.FragmentError;

import java.util.ArrayList;
import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

public class GetDataViewModel {
    public static RealmResults<Character> allCharacters(Context context){
        Realm realm = RealmConfig.getInstance(context);

        try {
            return realm.where(Character.class).findAll();

        }catch (Exception e){
            e.printStackTrace();
            FragmentError.build(((AppCompatActivity) context).getSupportFragmentManager(), e.toString());
            return null;
        }
    }

    public static List<Character> studentsCharacters(Context context){
        try (Realm realm = RealmConfig.getInstance(context)) {
            return new ArrayList<>(realm.where(Character.class)
                    .equalTo("hogwartsStudent", true)
                    .findAll());

        } catch (Exception e) {
            e.printStackTrace();
            FragmentError.build(((AppCompatActivity) context).getSupportFragmentManager(), e.toString());

            return null;
        }
    }

    public static List<Character> charactersByHouse(String house, Context context){
        try (Realm realm = RealmConfig.getInstance(context)) {
            return new ArrayList<>(realm.where(Character.class)
                    .equalTo("house", house, Case.INSENSITIVE)
                    .findAll());

        } catch (Exception e) {
            e.printStackTrace();
            FragmentError.build(((AppCompatActivity) context).getSupportFragmentManager(), e.toString());

            return null;
        }
    }

    public static List<Character> charactersByHouseAndStudentsOnly(String house, Context context){
        try (Realm realm = RealmConfig.getInstance(context)) {
            return new ArrayList<>(realm.where(Character.class)
                    .equalTo("house", house, Case.INSENSITIVE)
                    .and()
                    .equalTo("hogwartsStudent", true)
                    .findAll());

        } catch (Exception e) {
            e.printStackTrace();
            FragmentError.build(((AppCompatActivity) context).getSupportFragmentManager(), e.toString());

            return null;
        }
    }

}

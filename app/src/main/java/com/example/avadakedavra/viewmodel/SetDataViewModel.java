package com.example.avadakedavra.viewmodel;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.avadakedavra.model.models.Character;
import com.example.avadakedavra.view.fragments.FragmentError;

import java.util.List;

import io.realm.Realm;

public class SetDataViewModel {
    public static boolean saveCharacters(List<Character> characters, Context context){
        final Realm realm = RealmConfig.getInstance(context);

        try{
            for (final Character character : characters){

                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realmEx) {
                        Number maxId = realmEx.where(Character.class).max("characterId");
                        long newKey = (maxId == null) ? 1 : maxId.intValue() + 1;

                        Character c = realmEx.createObject(Character.class, newKey);
                        c.setDateOfBirth(character.getDateOfBirth());
                        c.setGender(character.getGender());
                        c.setHogwartsStudent(character.isHogwartsStudent());
                        c.setHouse(character.getHouse());
                        c.setImage(character.getImage());
                        c.setName(character.getName());
                        c.setPatronus(character.getPatronus());
                    }
                });

            }
            return true;

        }catch (Exception e){
            FragmentError.build(((AppCompatActivity) context).getSupportFragmentManager(), e.toString());
            e.printStackTrace();
            return false;

        }finally {
            realm.close();
        }
    }

    public static boolean deleteAll(Context context){
        Realm realm = RealmConfig.getInstance(context);

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

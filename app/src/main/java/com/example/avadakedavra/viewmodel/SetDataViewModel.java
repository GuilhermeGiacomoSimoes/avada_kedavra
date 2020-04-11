package com.example.avadakedavra.viewmodel;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.avadakedavra.model.models.Character;
import com.example.avadakedavra.view.adapter.CharactersAdapter;
import com.example.avadakedavra.view.fragments.FragmentError;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

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
                        character.setCharacterId(newKey);
                        realmEx.copyToRealmOrUpdate(character);
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
            RealmResults<Character> results = realm.where(Character.class).findAll();
            realm.beginTransaction();
            results.deleteAllFromRealm();
            realm.commitTransaction();

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

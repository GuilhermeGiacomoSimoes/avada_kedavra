package com.example.avadakedavra.model.bd;

import android.content.Context;

import io.realm.Realm;

public class RealmConfig {

    public static Realm getInstance(Context context){
        Realm.init(context);
        return Realm.getDefaultInstance();
    }

}

package com.example.avadakedavra.viewmodel;

import io.realm.Realm;

public class RealmConfig {
    public static Realm getInstance(){
        return Realm.getDefaultInstance();
    }
}

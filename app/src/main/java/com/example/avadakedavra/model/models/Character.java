package com.example.avadakedavra.model.models;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Character extends RealmObject {

    @PrimaryKey
    private long characterId;
    private String name;
    private String gender;
    private String house;
    private String dateOfBirth;
    private String patronus;
    private String image;
    private boolean hogwartsStudent;

    public Character(long characterId,
                     String name,
                     String gender,
                     String house,
                     String dateOfBirth,
                     String patronus,
                     String image)
    {
        this.characterId = characterId;
        this.name = name;
        this.gender = gender;
        this.house = house;
        this.dateOfBirth = dateOfBirth;
        this.patronus = patronus;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPatronus() {
        return patronus;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
    }

    public boolean isHogwartsStudent() {
        return hogwartsStudent;
    }

    public void setHogwartsStudent(boolean hogwartsStudent) {
        this.hogwartsStudent = hogwartsStudent;
    }
}

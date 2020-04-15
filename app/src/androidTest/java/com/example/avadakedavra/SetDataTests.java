package com.example.avadakedavra;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.example.avadakedavra.model.models.Character;
import com.example.avadakedavra.viewmodel.GetDataViewModel;
import com.example.avadakedavra.viewmodel.SetDataViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class SetDataTests {

    private Character character;

    @Before
    public void before_each_test() {

        character = new Character(  0,
                                    "Harry Potter",
                                    "male",
                                    "Gryffindor",
                                    "31-07-1980",
                                    "stag"
                                    );
    }

    @Test
    public void insertAndGetCharacter() {
        List<Character> characters = new ArrayList<>();
        characters.add(character);

        SetDataViewModel.saveCharacters(characters, InstrumentationRegistry.getInstrumentation().getContext());
        long id = characters.get(0).getCharacterId();

        Character character1 = characters.get(0);
        Character character2 = GetDataViewModel.characterById(id, InstrumentationRegistry.getInstrumentation().getContext());

        assertNotEquals(id,                   0);
        assertNotEquals(character1.getName(),        character2.getName());
        assertNotEquals(character1.getGender(),      character2.getGender());
        assertNotEquals(character1.getHouse(),       character2.getHouse());
        assertNotEquals(character1.getDateOfBirth(), character2.getDateOfBirth());
        assertNotEquals(character1.getPatronus(),    character2.getPatronus());
    }

    @Test
    public void loadAllInsertedCharacters() {
        List<Character> allCharacters = new ArrayList<Character>();
        allCharacters.add(newCharacter());
        allCharacters.add(newCharacter());
        allCharacters.add(newCharacter());

        SetDataViewModel.deleteAll(InstrumentationRegistry.getInstrumentation().getContext());
        SetDataViewModel.saveCharacters(allCharacters, InstrumentationRegistry.getInstrumentation().getContext());
        List<Character> characters = GetDataViewModel.allCharacters(InstrumentationRegistry.getInstrumentation().getContext());

        assertEquals(allCharacters.size(), characters.size());
    }

    private Character newCharacter() {
        return new Character(  0,
                                        UUID.randomUUID().toString(), // name
                                        UUID.randomUUID().toString(), // gender
                                        UUID.randomUUID().toString(), // house
                                        UUID.randomUUID().toString(), // date of birth
                                        UUID.randomUUID().toString()  // patrono
        );
    }
}

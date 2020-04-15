package com.example.avadakedavra;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.avadakedavra.model.models.Character;
import com.example.avadakedavra.view.activitys.MainActivity;
import com.example.avadakedavra.viewmodel.GetDataViewModel;
import com.example.avadakedavra.viewmodel.SetDataViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class SetDataTests {

    private Character character;
    
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = 
            new ActivityTestRule<MainActivity>(MainActivity.class);

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

        SetDataViewModel.saveCharacters(characters, activityActivityTestRule.getActivity());
        Long id = characters.get(0).getCharacterId();

        Character character1 = characters.get(0);
        Character character2 = GetDataViewModel.characterById(id,   activityActivityTestRule.getActivity());

        assertEquals(id,                          character2.getCharacterId());
        assertEquals(character1.getName(),        character2.getName());
        assertEquals(character1.getGender(),      character2.getGender());
        assertEquals(character1.getHouse(),       character2.getHouse());
        assertEquals(character1.getDateOfBirth(), character2.getDateOfBirth());
        assertEquals(character1.getPatronus(),    character2.getPatronus());
    }

    @Test
    public void loadAllInsertedCharacters() {
        new Runnable() {
            @Override
            public void run() {
                List<Character> allCharacters = new ArrayList<Character>();
                allCharacters.add(newCharacter());
                allCharacters.add(newCharacter());
                allCharacters.add(newCharacter());

                SetDataViewModel.deleteAll(  activityActivityTestRule.getActivity());
                SetDataViewModel.saveCharacters(allCharacters,   activityActivityTestRule.getActivity());
                List<Character> characters = GetDataViewModel.allCharacters(  activityActivityTestRule.getActivity());

                assertEquals(allCharacters.size(), characters.size());
            }
        };
    }

    private Character newCharacter() {
        return new Character(   new Random().nextLong(),
                                UUID.randomUUID().toString(), // name
                                UUID.randomUUID().toString(), // gender
                                UUID.randomUUID().toString(), // house
                                UUID.randomUUID().toString(), // date of birth
                                UUID.randomUUID().toString()  // patrono
                            );
    }
}

package com.example.avadakedavra.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.avadakedavra.R;

public class FragmentCharacterDetail extends DialogFragment {

    Character character;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_character_detail, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static void buildFragment(final Character characterDetail, final FragmentManager fragmentManager){
        new FragmentCharacterDetail() {{

            if(!isAdded()){
                this.character = characterDetail;

                if(fragmentManager != null){
                    show(fragmentManager, "characterDetail");
                }
            }

        }};
    }

}

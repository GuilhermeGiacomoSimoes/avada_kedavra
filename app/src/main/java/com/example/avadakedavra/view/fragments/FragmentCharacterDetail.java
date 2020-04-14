package com.example.avadakedavra.view.fragments;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.avadakedavra.R;
import com.example.avadakedavra.databinding.FragmentCharacterDetailBinding;
import com.example.avadakedavra.model.models.Character;

public class FragmentCharacterDetail extends DialogFragment {

    Character character;

//    private void getScreenSizeConfigureDialog(View view) {
//        try{
//            WindowManager wm = (WindowManager) getContext().getSystemService(getContext().WINDOW_SERVICE);
//            Display display = wm.getDefaultDisplay();
//            DisplayMetrics metrics = new DisplayMetrics();
//            display.getMetrics(metrics);
//
//            int width = (int) Math.round(metrics.widthPixels * 0.8);
//
//            view.findViewById(R.id.characterDetails).getLayoutParams().width = 104;
//        }
//        catch (Exception e){
//            FragmentError.build(((AppCompatActivity)getContext()).getSupportFragmentManager(), e.toString());
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentCharacterDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_detail, container, false);
        binding.setCharacter(character);

//        getScreenSizeConfigureDialog(binding.getRoot());

        return binding.getRoot();
    }

    public static void buildFragment(@NonNull final Character characterDetail, @NonNull final FragmentManager fragmentManager){
        FragmentCharacterDetail fragmentCharacterDetail = new FragmentCharacterDetail();
        if(!fragmentCharacterDetail.isAdded()){
            fragmentCharacterDetail.character = characterDetail;
            fragmentCharacterDetail.show(fragmentManager, "characterDetail");
        }
    }

}

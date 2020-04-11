package com.example.avadakedavra.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.avadakedavra.R;
import com.example.avadakedavra.databinding.FragmentErrorBinding;

public class FragmentError extends DialogFragment {

    private String error;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentErrorBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_error, container, false);
        binding.setError(this);

        return binding.getRoot();
    }

    public String getError(){
        return this.error;
    }

    protected void setError(String error){
        this.error = error;
    }

    public void dismiss(View view){
        super.dismiss();
    }

    public static void build(@NonNull final FragmentManager fragmentManager, @NonNull final String error) {
        FragmentError instance = new FragmentError();
        instance.setError(error);
        instance.show(fragmentManager, error);
    }
}

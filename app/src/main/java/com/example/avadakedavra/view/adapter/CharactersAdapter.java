package com.example.avadakedavra.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.avadakedavra.R;
import com.example.avadakedavra.model.Character;

import java.util.List;

public class CharactersAdapter extends BaseAdapter {

    private Context context;
    private List<Character> characters;

    public CharactersAdapter(Context context, List<Character> characters){
        this.context = context;
        this.characters = characters;
    }

    @Override
    public int getCount() {
        return characters.size();
    }

    @Override
    public Object getItem(int position) {
        characters.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return View.inflate(context, R.layout.characters_adapter, null);
    }
}

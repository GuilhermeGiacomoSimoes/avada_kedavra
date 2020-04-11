package com.example.avadakedavra.model.http;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.avadakedavra.R;
import com.example.avadakedavra.helper.ConnectionHelper;
import com.example.avadakedavra.model.models.Character;
import com.example.avadakedavra.view.fragments.FragmentError;
import com.example.avadakedavra.viewmodel.SetDataViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class GetData {
    private Context context;
    private boolean isRunning = false;

    public GetData(@NonNull Context context){
        this.context = context;
        if(!isRunning)
            new GetDataAsyncTask().execute();
    }

    public class GetDataAsyncTask extends AsyncTask<String, Void, List<Character>> {
        private Gson gson;

        private AsyncHttpResponseHandler getDataResponseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    gson = new Gson();

                    Type listType = new TypeToken<ArrayList<Character>>(){}.getType();
                    String response = new String(responseBody);
                    List<Character> characters = gson.fromJson(response, listType);
//                    SetDataViewModel.saveCharacters(characters, context);

                    if(SetDataViewModel.deleteAll(context)){
                        SetDataViewModel.saveCharacters(characters, context);
                    }

                }catch (Exception e){
                    FragmentError.build(((AppCompatActivity) context).getSupportFragmentManager(), context.getResources().getString(R.string.error) + e.toString());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                FragmentError.build(((AppCompatActivity) context).getSupportFragmentManager(), context.getResources().getString(R.string.couldnt_get_data));
                error.printStackTrace();
            }
        };

        @Override
        protected List<Character> doInBackground(String... strings) {
            isRunning = true;

            gson =  new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();

            try{
                if(!ConnectionHelper.isConnected(context)){
                    FragmentError.build(((AppCompatActivity) context).getSupportFragmentManager(), context.getResources().getString(R.string.no_internet_connection));
                    return null;
                }

                AsyncHttpClient httpClient = new AsyncHttpClient();
                httpClient.setTimeout(15*60*1000);
                httpClient.get(context, ConnectionHelper.ALLCHARACTERS, null, "application/json", getDataResponseHandler);


            }catch (Exception e){
                FragmentError.build(((AppCompatActivity) context).getSupportFragmentManager(), context.getResources().getString(R.string.error) + e.toString());
                e.printStackTrace();
            }

            return null;
        }
    }
}

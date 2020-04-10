package com.example.avadakedavra.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.avadakedavra.Helper.ConnectionHelper;
import com.example.avadakedavra.Helper.HouseENUM;
import com.example.avadakedavra.Helper.RequestENUM;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class GetData {
    private Context context;
    private String url;
    private static String GET_DATA = "GET";

    public GetData(@NonNull Context context, HouseENUM house, @NonNull RequestENUM requestENUM){
        this.context = context;

        if(requestENUM == RequestENUM.STUDENT){
            url = ConnectionHelper.STUDENTCHARACTERS;
        }
        else if(requestENUM == RequestENUM.ALL){
            url = ConnectionHelper.ALLCHARACTERS;
        }
        else {
            if(house != null){
                url = ConnectionHelper.CHARACTERSBYHOUSE + house.name();
            }
            else {
                //TODO implementar modal de erro
                Log.e("ERRO", "house nulo");
            }
        }

        if(url != null){
            new GetDataAsyncTask().execute(GET_DATA);
        }
        else {
            //TODO implementar modal de erro
            Log.e("ERRO", "url nula");
        }

    }

    private class GetDataAsyncTask extends AsyncTask<String, Void, List<Character>> {
        private Gson gson;

        private AsyncHttpResponseHandler getDataResponseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    gson =  new GsonBuilder()
                            .excludeFieldsWithoutExposeAnnotation()
                            .create();

                    Type listType = new TypeToken<ArrayList<Character>>(){}.getType();
                    String response = new String(responseBody);
                    List<Character> characters = gson.fromJson(response, listType);

                    //TODO armazenar os characters no banco
                }catch (Exception e){
                    //TODO implementar um modal de erro
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //TODO implementar um modal de erro
                error.printStackTrace();
            }
        };

        @Override
        protected List<Character> doInBackground(String... strings) {

            gson =  new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();

            try{
                if(!ConnectionHelper.isConnected(context)){
                    //TODO implementar um modal de erro
                    //SEM CONEXÃO COM A INTERNET
                    return null;
                }

                SyncHttpClient httpClient = new SyncHttpClient();
                httpClient.setTimeout(15*60*1000);

                if(strings[0].equals(GET_DATA)){
                    httpClient.get(context, url, null, "application/json", getDataResponseHandler);
                }

            }catch (Exception e){
                //TODO implementar um modal de erro
                e.printStackTrace();
            }

            return null;
        }
    }
}

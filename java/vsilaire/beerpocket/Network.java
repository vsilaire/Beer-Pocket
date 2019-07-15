package vsilaire.beerpocket;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vsilaire.beerpocket.Model.Beer;
import vsilaire.beerpocket.Model.Brewery;

/**
 * Singleton Network, utilise l'interface Network Interface
 * @see NetworkInterface
 */
public class Network {

    final private String API_URL;
    final private String API_KEY;

    private static Network itself;
    private Retrofit retrofit;
    private boolean terminated = false;
    private boolean failed = false;

    private Context context;

    private GsonHolder.AllBeers holderAllBeers;
    private GsonHolder.OneBeer holderOneBeer;
    private GsonHolder.Breweries holderBreweries;

    private ProgressDialog progressDialog;



    private class MyAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            //displayProgressDialog();

        }

        /**
         * @param parameters les paramètres de la requête
         * @return "terminated" à la fin de la methode
         */
        @Override
        protected String doInBackground(String... parameters) {


            terminated = false;

            if(retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

            NetworkInterface networkInterface = retrofit.create(NetworkInterface.class);

            retrofit.create(NetworkInterface.class);


            if(parameters[0] =="beers"){
                Call<GsonHolder.AllBeers> call = networkInterface.getBeers(API_KEY, parameters[2]);
                try {
                    Response<GsonHolder.AllBeers> response = call.execute();
                    if(response.body() != null){
                        holderAllBeers = response.body();
                    }
                    else {
                        failed = true;
                        Log.e("NETWORK:", "Server Error:" + response.errorBody().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(parameters[0] == "beer"){
                String id = parameters[1];
                Call<GsonHolder.OneBeer> call = networkInterface.getBeer(id, API_KEY);
                try {
                    Response<GsonHolder.OneBeer> response = call.execute();
                    if(response.body() != null){
                        holderOneBeer = response.body();
                    }
                    else {
                        failed = true;
                        Log.e("NETWORK:", "Server Error:" + response.errorBody().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(parameters[0] == "breweries"){
                String id = parameters[1];
                Call<GsonHolder.Breweries> call = networkInterface.getBreweries(id, API_KEY);
                try {
                    Response<GsonHolder.Breweries> response = call.execute();
                    if(response.body() != null){
                        holderBreweries = response.body();
                    }
                    else {
                        failed = true;
                        Log.e("NETWORK:", "Server Error:" + response.errorBody().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            terminated = true;
            return "terminated";
        }
        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);

            //progressDialog.dismiss();

        }
    }

    /**
     * Lance la requête d'une seule bière.
     * @param id
     * @return la bière demandée
     */
    public Beer getBeer(String id){

        this.request(new String[]{"beer", id, "0"});
        return this.holderOneBeer.getBeerData();
    }

    /**
     * Lance la requête de toutes les bières, selon la page demandée.
     * @param page
     * @return
     */
    public List<Beer> getBeers(int page){
        String[] parameters = {"beers", null, String.valueOf(page)};
        this.request(parameters);
        return this.holderAllBeers.getListData();
    }

    public List<Brewery> getBreweries(String id){

        this.request(new String[]{"breweries", id, "0"});
        return this.holderBreweries.getListData();
    }


    private Network(Context context){
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        API_URL = "HTTPS://api.brewerydb.com/v2/";
        API_KEY = "";
    }

    /**
     * Methode de création du singleton
     * @return l'instance du Network
     */
    public static Network getInstance(Context context){

        if(itself == null){
            itself = new Network(context);
        }
        return itself;
    }

    /**
     * Execute la requête Retrofit en passant par une tâche assynchrone
     * @see MyAsyncTask
     * @param parameters
     * @param
     */
    private void request(String[] parameters){

        try {
            if(new MyAsyncTask().execute(parameters).get()=="terminated");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param url
     * @return l'image stockée à l'adresse de l'URL
     */
    public Bitmap loadImage(URL url){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            return bmp;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("NETWORK:", "Failed to load image");
            return null;
        }
    }

    public void displayProgressDialog (){

        String pdTitle = context.getString(R.string.loading);
        String pdMessage = context.getString(R.string.loading_message);
        progressDialog = ProgressDialog.show(context, pdTitle, pdMessage);
    }




}


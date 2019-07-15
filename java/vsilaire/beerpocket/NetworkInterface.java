package vsilaire.beerpocket;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interface prévue pour Retrofit
 * @see Network
 */
public interface NetworkInterface {

    /**
     * Requête de toutes les bières
     * @param api_key
     * @param page page demandée
     * @return l'ensemble des bière pour telle page
     */
    @GET("beers/")
    Call<GsonHolder.AllBeers> getBeers(@Query("key") String api_key, @Query("p") String page);

    /**
     * Requête d'une seule bière en fonction de son identifiant
     * @param id identifiant de la bière
     * @param api_key
     * @return la bière demandée
     */
    @GET("beer/{beer}/")
    Call<GsonHolder.OneBeer> getBeer(@Path("beer") String id, @Query("key") String api_key);

    /**
     * Requête de toutes les brasseries pour telle bière
     * @param id identifiant de la bière dont ont demande les brasseries
     * @param api_key
     * @return la liste de brasseries relatives à cette bière
     */
    @GET("beer/{beer}/breweries")
    Call<GsonHolder.Breweries> getBreweries(@Path("beer") String id, @Query("key") String api_key);


}

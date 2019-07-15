package vsilaire.beerpocket;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import vsilaire.beerpocket.Model.Beer;
import vsilaire.beerpocket.Model.Brewery;

/**
 * Classe servant de reception à la réponse de Retrofit
 */
public class GsonHolder {

    @SerializedName("status")
    private String status;

    /**
     * Classe utilisée dans le cas d'une requête de toutes les bières
     */
    public class AllBeers extends GsonHolder {

        @SerializedName("currentPage")
        private int currentPage;

        @SerializedName("numberOfPages")
        private int numberOfPages;

        @SerializedName("totalResults")
        private int totalResults;

        @SerializedName("data")
        private List<Beer> data;


        public int getCurrentPage() {
            return currentPage;
        }

        public int getNumberOfPages() {
            return numberOfPages;
        }

        public int getTotalResults() {
            return totalResults;
        }

        /**
         * @return La liste de bières actuelle contenue dans le Gson
         */
        public List<Beer> getListData() {
            return data;
        }


    }

    /**
     * Classe utilisée dans le cas d'une requête d'une seule bière
     */
    public class OneBeer extends GsonHolder {

        @SerializedName("message")
        private String message;


        @SerializedName("data")
        private Beer beer;


        /**
         * @param data
         */
        public OneBeer(Beer data) {
            this.beer = data;
        }

        /**
         * @return la bière contenue dans le Gson
         */
        public Beer getBeerData() {
            return this.beer;
        }

        public String getMessage() {
            return this.message;
        }

    }

    /**
     * Classe utilisée dans le cas d'une requête contenant une ou plusieurs brasseries
     */
    public class Breweries extends GsonHolder {

        @SerializedName("data")
        private List<Brewery> data;

        public List<Brewery> getListData(){
            return this.data;
        }

    }

}
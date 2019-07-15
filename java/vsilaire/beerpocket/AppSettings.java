package vsilaire.beerpocket;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

import vsilaire.beerpocket.UI.DetailsActivity;
import vsilaire.beerpocket.UI.MainActivity;

/**
 * Classe servant de gestionnaire des paramètres de l'application
 */
public class AppSettings {

    private static AppSettings itself;

    private static Locale appLanguage;
    private static int fontMod;

    private static Context context;

    private AppSettings(Context _context) {

        if(_context != null){
            context = _context;
            appLanguage = context.getResources().getConfiguration().locale;
        }
    }

    public static AppSettings getInstance(Context _context){


        if(itself == null){
            itself = new AppSettings(_context);
        }

        return itself;
    }


    public int getAppLanguage(){
        if(appLanguage.getCountry() == "fr"){
            return 1;
        }
        if(appLanguage.getCountry() == "en"){
            return 0;
        }
        return 3;
    }

    /**
     * [NON FONCTIONNEL POUR LE MOMENT]
     * Methode de switch de la langue entre anglais et français
     * @return 1 si l'application est mise en français, 0 si l'application est mise en anglais
     */
    public int switchLanguage(){

        Configuration config = new Configuration();
        int res = 3;
        if(appLanguage.getLanguage() == "en_EN"){
            appLanguage = Locale.FRENCH;
            config.locale = Locale.FRENCH;
            Log.i("APP_SETTINGS:", "French selected");
            res = 1;
        }
        else {
            appLanguage = Locale.ENGLISH;
            config.locale = appLanguage;
            Log.i("APP_SETTINGS:", "English selected");
            res = 0;
        }
        context.getResources().updateConfiguration(config, null);
        if (MainActivity.started()) {
            MainActivity.refresh(context);
        }
        if (DetailsActivity.started()) {
            DetailsActivity.refresh(context);
        }
        return res;
    }

    public void displayToast(String text){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

}

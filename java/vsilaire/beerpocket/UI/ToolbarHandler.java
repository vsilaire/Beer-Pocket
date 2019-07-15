package vsilaire.beerpocket.UI;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vsilaire.beerpocket.AppSettings;
import vsilaire.beerpocket.R;

/**
 * Classe servant de gestionnaire à la barre permettant de changer la langue et de rechercher une bière.
 */
class ToolbarHandler {

    private AppSettings settings;
    private Activity activity;

    private android.support.v7.widget.Toolbar toolbar;
    private Button languageBT;
    private Button searchBT;
    private EditText searchET;

    @TargetApi(Build.VERSION_CODES.O)
    public ToolbarHandler(Activity activity,
                          int toolbarId,
                          int languageBTId,
                          int searchBTId,
                          int searchETId){

        this.activity = activity;
        this.toolbar = this.activity.findViewById(toolbarId);
        this.languageBT = this.activity.findViewById(languageBTId);
        this.searchBT = this.activity.findViewById(searchBTId);
        this.searchET = this.activity.findViewById(searchETId);

        this.settings = AppSettings.getInstance(null);

        this.initListeners();
    }

    /**
     * Initialise les actions sur les boutons. Le changement de langue n'est pas fonctionnel.
     * @see AppSettings
     */
    private void initListeners(){
        this.languageBT.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                int select = settings.switchLanguage();
                switch (select){
                    case 0:
                        languageBT.setBackground(activity.getDrawable(R.drawable.english_flag));
                        break;
                    case 1:
                        languageBT.setBackground(activity.getDrawable(R.drawable.french_flag));
                        break;
                }
            }
        });

        this.searchBT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(searchET.getText().toString().length() > 2){

                }
            }
        });
    }

    public android.support.v7.widget.Toolbar getToolbar() {
        return toolbar;
    }

    public Button getLanguageBT() {
        return languageBT;
    }

    public Button getSearchBT() {
        return searchBT;
    }

    public EditText getSearchET() {
        return searchET;
    }
}

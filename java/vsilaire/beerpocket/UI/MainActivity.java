package vsilaire.beerpocket.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import vsilaire.beerpocket.AppSettings;
import vsilaire.beerpocket.Model.Beer;
import vsilaire.beerpocket.Network;
import vsilaire.beerpocket.R;


public class MainActivity extends Activity {

    private RecyclerView recyclerView;

    private AppSettings settings;
    private Network network;

    private int page = 1;
    private Button previousPageButton;
    private Button nextPageButton;
    private TextView pageTV;

    private ListAdapter listAdapter;

    private static Context context;

    private List<Beer> beerList;

    private ToolbarHandler tbHandler;
    private LinearLayout mainLayout;

    private static boolean isStarted;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //UI init -----------------------------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //-------------------------------------
        isStarted = true;
        context = this.getApplicationContext();
        //UI elements init -------------------------------------------
        mainLayout = findViewById(R.id.main_activity_layout);
        pageTV = findViewById(R.id.page_tv);

        //------------------------------------------------------------

        this.initButtons();
        this.initData();
    }

    private void initButtons(){

        previousPageButton = findViewById(R.id.previous_page_button);
        nextPageButton = findViewById(R.id.next_page_button);

        previousPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page != 1){
                    page --;
                    beerList = network.getBeers(page);
                    updateRecyclerView();
                    pageTV.setText(String.valueOf(page));
                }
                else {
                    settings.displayToast(getString(R.string.first_page_notif));
                }
            }
        });

        nextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page != 23){
                    page ++;
                    beerList = network.getBeers(page);
                    updateRecyclerView();
                    pageTV.setText(String.valueOf(page));
                }
                else {
                    settings.displayToast(getString(R.string.last_page_notif));
                }

            }
        });
    }

    /**
     * Methode d'initialisation des données de l'activité principale
     */
    private void initData(){

        //Initialisation du singleton Network
        network = Network.getInstance(this);

        settings = AppSettings.getInstance(this);

        this.tbHandler = new ToolbarHandler(
                this,
                R.id.main_activity_search_view,
                R.id.language_button,
                R.id.search_button,
                R.id.search_edit_text);

        //Requête des données de la liste des bières
        beerList = network.getBeers(this.page);
        pageTV.setText(String.valueOf(page));
        initRecyclerView();
        notifyListAdapter();
    }

    @Override
    public void onStop() {

        super.onStop();
        isStarted = false;
    }

    /**
     * Methode d'initialisation du RecyclerView
     */
    private void initRecyclerView(){

        recyclerView = findViewById(R.id.recycler_view);

        listAdapter = new ListAdapter(beerList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(listAdapter);
    }

    /**
     * Methode de notification de changement de la liste de bières
     */
    private void notifyListAdapter(){

        listAdapter.notifyDataSetChanged();
    }

    /**
     * Methode de mise à jour du RecyclerView
     */
    private void updateRecyclerView(){
        listAdapter = new ListAdapter(beerList);
        notifyListAdapter();
        recyclerView.setAdapter(listAdapter);
    }


    public static void refresh(Context context) {

        Intent refresh = new Intent(context, MainActivity.class);
        context.startActivity(refresh);
    }

    public static boolean started(){

        return isStarted;
    }

    public static Context getContext(){
        return context;
    }
}
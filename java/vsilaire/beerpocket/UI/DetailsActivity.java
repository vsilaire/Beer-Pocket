package vsilaire.beerpocket.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.net.URL;
import java.util.List;

import vsilaire.beerpocket.Model.Beer;
import vsilaire.beerpocket.Model.Brewery;
import vsilaire.beerpocket.Network;
import vsilaire.beerpocket.R;

/**
 * Activité des détails d'une bière
 */
public class DetailsActivity extends Activity {

    private String id;
    private Beer beer;

    private Network network;
    private TextView nameTV;
    private TextView abvTV;
    private TextView descriptionTV;
    private TextView breweriesTV;
    private ImageView pictureIV;

    private ScrollView scrollView;
    private ToolbarHandler tbHandler;

    private static boolean isStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        this.tbHandler = new ToolbarHandler(
                this,
                R.id.details_search_view,
                R.id.language_button,
                R.id.search_button,
                R.id.search_edit_text);

        this.scrollView =  findViewById(R.id.details_scrollview);
        this.nameTV = findViewById(R.id.details_beer_name_tv);
        this.abvTV = findViewById(R.id.details_abv_tv);
        this.descriptionTV = findViewById(R.id.details_description_tv);
        this.breweriesTV = findViewById(R.id.details_breweries_lv);
        this.pictureIV = findViewById(R.id.details_picture_iv);


        Intent intent = getIntent();
        this.id = intent.getStringExtra("id");
        this.network = Network.getInstance(this);

        this.beer = this.network.getBeer(id);

        this.nameTV.setText(beer.getName());
        this.abvTV.setText(Float.toString(beer.getAbv()) + "%");

        List<Brewery> breweries = network.getBreweries(beer.getId());
        for(Brewery b : breweries){
            this.breweriesTV.append(b.getName() + "\n");
        }

        if(beer.getDescription() != null) {
            this.descriptionTV.setText(beer.getDescription());
        }
        else {
            this.descriptionTV.setText(R.string.no_available_description);

        }

        //Affichage de l'image si existante
        if(this.beer.haveLabels()) {
            this.pictureIV.setMinimumHeight(1400);
            Bitmap bmp;

            URL url = beer.getLargeLabel();
            if(url == null){
                url = beer.getMediumLabel();
                if(url == null){
                    url = beer.getIconLabel();
                }
            }

            if(url != null) {
                bmp = network.loadImage(url);
                this.pictureIV.setImageBitmap(bmp);
            }
        }
        else {

            this.pictureIV.setAdjustViewBounds(false);

            //Correction d'affichage en cas d'absence d'image
            this.pictureIV.setPadding(
                    this.pictureIV.getPaddingLeft(),
                    64,
                    this.pictureIV.getPaddingRight(),
                    this.pictureIV.getPaddingBottom());
            this.pictureIV.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onStart() {

        super.onStart();
        this.nameTV.requestFocus();
        isStarted = true;
    }

    @Override
    public void onBackPressed(){
        this.finish();
    }



    public static void refresh(Context context){

        Intent refresh = new Intent(context, DetailsActivity.class);
        context.startActivity(refresh);
    }

    @Override
    public void onStop() {

        super.onStop();
        isStarted = false;
    }

    public static boolean started(){
        return isStarted;
    }
}

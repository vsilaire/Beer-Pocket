package vsilaire.beerpocket.UI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import vsilaire.beerpocket.Model.Beer;
import vsilaire.beerpocket.Network;
import vsilaire.beerpocket.R;

/**
 * Classe gestionnaire du RecyclerView
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<Beer> beerList;
    private ArrayList<Bitmap> iconList;
    private Network network;
    private View view;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtra("id", id);
                v.getContext().startActivity(intent);
                Log.i("LISTADAPTER:", "Activity started");
            }
        };

        private String id;
        private TextView nameTV, abvTV;
        private ImageView pictureIV;

        public MyViewHolder(View _view) {
            super(_view);
            view = _view;
            view.setOnClickListener(onClickListener);
            this.nameTV =  view.findViewById(R.id.beer_name_tv);
            this.abvTV = view.findViewById(R.id.abv_tv);
            this.pictureIV = view.findViewById(R.id.picture_iv);
        }
    }

    public ListAdapter(List<Beer> beerList){

        this.network = Network.getInstance(MainActivity.getContext());
        this.beerList = beerList;
        this.initIconList();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.beer_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Beer beer = this.beerList.get(position);
        holder.id = beer.getId();

        holder.nameTV.setText(beer.getName());
        float abv = beer.getAbv();
        if(abv == 0){
            holder.abvTV.setText(R.string.non_alcoholic);
        }
        else {
            holder.abvTV.setText(Float.toString(abv) + "%");
        }

        if(iconList.get(position) != null){

            holder.pictureIV.setImageBitmap(iconList.get(position));
        }
        else {
            Bitmap icon = BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.beer_ico);
            holder.pictureIV.setImageBitmap(icon);
        }

    }

    @Override
    public int getItemCount() {
        if(beerList != null) {
            return beerList.size();
        }
        else return 0;
    }

    /**
     * Methode servant à éviter le chargement des images pendant l'affichage des items, en initialisant une liste d'images.
     */
    private void initIconList(){
        iconList = new ArrayList<Bitmap>();
        for(Beer beer : beerList){
            Bitmap icon = null;
            URL url = beer.getIconLabel();
            if(url != null){
                icon = this.network.loadImage(url);
                if(icon == null) {
                    Log.i("LISTADAPTER:", "null image");
                }
            }
            this.iconList.add(icon);
        }
    }
}

package vsilaire.beerpocket.Model;

import com.google.gson.annotations.SerializedName;

public class Brewery {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("nameShortDisplay")
    private String nameShortDisplay;

    @SerializedName("status")
    private String status;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameShortDisplay() {
        return nameShortDisplay;
    }
}

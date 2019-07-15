package vsilaire.beerpocket.Model;

import com.google.gson.annotations.SerializedName;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Beer {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("nameDiplay")
    private String nameDisplay;

    @SerializedName("abv")
    private float abv;

    @SerializedName("glasswareId")
    private int glasswareId;

    @SerializedName("styleId")
    private int styleId;

    @SerializedName("isOrganic")
    private boolean isOrganic;

    @SerializedName("isRetired")
    private boolean isRetired;

    @SerializedName("status")
    private String status;

    @SerializedName("statusDisplay")
    private String statusDisplay;

    @SerializedName("createDate")
    private String createDate;

    @SerializedName("updateDate")
    private String updateDate;

    @SerializedName("labels")
    private LabelsCollection labels;

    @SerializedName("description")
    private String description;

    private class LabelsCollection {

        @SerializedName("icon")
        private String icon;

        @SerializedName("medium")
        private String medium;

        @SerializedName("large")
        private String large;

        public String getIcon() {
            return icon;
        }

        public String getMedium() {
            return medium;
        }

        public String getLarge() {
            return large;
        }

    }

    public Beer(String id, String name, float abv, LabelsCollection labels){
        this.id = id;
        this.nameDisplay = name;
        this.name = name;
        this.abv = abv;
        this.labels = labels;
    }

    public List<String> getBreweries() {
        return breweries;
    }

    public void setBreweries(List<String> breweries) {
        this.breweries = breweries;
    }

    private List<String> breweries;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameDisplay() {
        return nameDisplay;
    }

    public float getAbv() {
        return abv;
    }

    public int getGlasswareId() {
        return glasswareId;
    }

    public int getStyleId() {
        return styleId;
    }

    public boolean isOrganic() {
        return isOrganic;
    }


    /*public List<String> getLabels() {
        return labels;
    }*/

    public URL getIconLabel(){
        return this.getLabel("icon");
    }

    public URL getMediumLabel(){
        return this.getLabel("medium");
    }

    public URL getLargeLabel(){
        return this.getLabel("large");
    }

    private URL getLabel(String labelType) {
        URL url = null;
        if(labels != null) {
            try {
                switch (labelType) {
                    case "icon":
                        url = new URL(labels.getIcon());
                        break;
                    case "medium":
                        url = new URL(labels.getMedium());
                        break;
                    case "large":
                        url = new URL(labels.getLarge());
                        break;
                    default:
                        url = new URL(labels.getIcon());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return url;
    }

    public boolean haveLabels(){
        return (labels != null);
    }

    public String getStatus() {
        return status;
    }

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getDescription(){
        return this.description;
    }
}

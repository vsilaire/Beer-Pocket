package vsilaire.beerpocket.Model;

public class Style {

    private int id;
    //private String category;
    private String name;
    private String shortName;
    private String description;
    private int ibuMin;
    private int ibuMax;
    private float abvMin;
    private float abvMax;
    private int srmMin;
    private int srmMax;
    private float ogMin;
    private float fgMin;
    private float fgMax;
    private String createDate;
    private String updateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIbuMin() {
        return ibuMin;
    }

    public void setIbuMin(int ibuMin) {
        this.ibuMin = ibuMin;
    }

    public int getIbuMax() {
        return ibuMax;
    }

    public void setIbuMax(int ibuMax) {
        this.ibuMax = ibuMax;
    }

    public float getAbvMin() {
        return abvMin;
    }

    public float getAbvMax() {
        return abvMax;
    }

    public int getSrmMin() {
        return srmMin;
    }

    public void setSrmMin(int srmMin) {
        this.srmMin = srmMin;
    }

    public int getSrmMax() {
        return srmMax;
    }

    public void setSrmMax(int srmMax) {
        this.srmMax = srmMax;
    }

    public float getOgMin() {
        return ogMin;
    }

    public void setOgMin(float ogMin) {
        this.ogMin = ogMin;
    }

    public float getFgMin() {
        return fgMin;
    }

    public void setFgMin(float fgMin) {
        this.fgMin = fgMin;
    }

    public float getFgMax() {
        return fgMax;
    }

    public void setFgMax(float fgMax) {
        this.fgMax = fgMax;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

}

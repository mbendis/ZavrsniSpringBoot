package hr.fer.marin.zavrsni.model;

public class ObjectWithPercentage {
    private Integer id;
    private String name;
    private String adress;
    private double longitude;
    private double latitude;
    private int percentage;
    private int userId;

    public ObjectWithPercentage(Object object, int percentage) {
        this.id = object.getId();
        this.name = object.getName();
        this.adress = object.getAdress();
        this.longitude = object.getLongitude();
        this.latitude = object.getLatitude();
        this.percentage = percentage;
        this.userId = object.getUser().getId();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}

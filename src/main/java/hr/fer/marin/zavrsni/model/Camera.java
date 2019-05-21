package hr.fer.marin.zavrsni.model;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "cameras")
public class Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private Integer statusId;
    private Integer objectId;

    public Camera() {
    }

    public Camera(String name, Integer statusId, Integer objectId) {
        this.name = name;
        this.statusId = statusId;
        this.objectId = objectId;
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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }
}

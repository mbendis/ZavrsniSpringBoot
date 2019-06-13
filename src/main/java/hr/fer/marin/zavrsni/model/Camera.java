package hr.fer.marin.zavrsni.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "cameras")
public class Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "status_id")
    private Integer statusId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "object_id", nullable = false)
    @JsonIgnore
    private Object object;

    public Camera() {
    }

    public Camera(String name, Integer statusId) {
        this.name = name;
        this.statusId = statusId;
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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

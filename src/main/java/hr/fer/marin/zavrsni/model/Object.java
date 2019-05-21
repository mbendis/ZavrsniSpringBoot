package hr.fer.marin.zavrsni.model;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "objects")
public class Object {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String adress;
    private Integer ownerId;

    public Object() {
    }

    public Object(String name, String adress, Integer ownerId) {
        this.name = name;
        this.adress = adress;
        this.ownerId = ownerId;
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

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}

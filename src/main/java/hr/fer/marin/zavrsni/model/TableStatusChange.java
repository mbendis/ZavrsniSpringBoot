package hr.fer.marin.zavrsni.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@javax.persistence.Table(name = "table_status_change")
public class TableStatusChange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "table_id", nullable = false)
    @JsonIgnore
    private Table table;

    private Boolean occupied;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public TableStatusChange() {
    }

    public TableStatusChange(Boolean occupied, Date time) {
        this.occupied = occupied;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}

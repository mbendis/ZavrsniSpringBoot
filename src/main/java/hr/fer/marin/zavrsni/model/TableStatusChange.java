package hr.fer.marin.zavrsni.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@javax.persistence.Table(name = "table_status_change")
public class TableStatusChange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer tableId;
    private Boolean occupied;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public TableStatusChange() {
    }

    public TableStatusChange(Integer tableId, Boolean occupied, Date time) {
        this.tableId = tableId;
        this.occupied = occupied;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
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
}

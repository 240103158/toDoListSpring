package kz.toDoList.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status", nullable = false)
    private RecordStatus status;

    @Column(name = "title", nullable = false, length = 55)
    private String title;

    public Record(){}

    public Record(String title) {
        this.title = title;
        this.status = RecordStatus.ACTIVE;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public RecordStatus getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }
}

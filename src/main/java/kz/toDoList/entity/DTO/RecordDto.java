package kz.toDoList.entity.DTO;

import kz.toDoList.entity.Record;
import kz.toDoList.entity.RecordStatus;

import java.util.List;

public class RecordDto {
    private final List<Record> records;
    private final int numberOfDone;
    private final int numberOfActive;

    public List<Record> getRecords() {
        return records;
    }

    public int getNumberOfDone() {
        return numberOfDone;
    }

    public int getNumberOfActive() {
        return numberOfActive;
    }

    public RecordDto(List<Record> records, int numberOfDone, int numberOfActive) {
        this.records = records;
        this.numberOfDone = numberOfDone;
        this.numberOfActive = numberOfActive;
    }

}

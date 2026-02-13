package kz.toDoList.service;

import kz.toDoList.DAO.RecordDao;
import kz.toDoList.entity.DTO.RecordDto;
import kz.toDoList.entity.Record;
import kz.toDoList.entity.RecordStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordService {
    private final RecordDao recordDao;

    @Autowired
    public RecordService(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public RecordDto findAll(String filterMode){
        List<Record> records = recordDao.findAll();
        int numberOfDone = (int)records.stream().filter(sc -> sc.getStatus() == RecordStatus.DONE).count();
        int numberOfActive = (int)records.stream().filter(sc -> sc.getStatus() == RecordStatus.ACTIVE).count();

        if(filterMode == null || filterMode.isBlank()){
            return new RecordDto(records, numberOfDone, numberOfActive);

        }

        String filterToUpperCase = filterMode.toUpperCase();

        List<String> allowedFilterModes = Arrays.stream(RecordStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        if(allowedFilterModes.contains(filterToUpperCase)){
            List<Record> filteredRecords =  records.stream()
                    .filter(sc -> sc.getStatus() == RecordStatus.valueOf(filterToUpperCase))
                    .collect(Collectors.toList());
            return new RecordDto(filteredRecords, numberOfDone, numberOfActive);
        }else{
            return new RecordDto(records, numberOfDone, numberOfActive);
        }
    }

    public void saveRecord(String title){
        if(!title.isEmpty())
            if(!title.isBlank()) {
                recordDao.saveRecord(new Record(title));
            }
    }

    public void deleteRecord(int id){
        recordDao.deleteRecord(id);
    }

    public void updateRecordStatus(int id, RecordStatus newStatus){
        recordDao.updateRecordStatus(id, newStatus);
    }
}

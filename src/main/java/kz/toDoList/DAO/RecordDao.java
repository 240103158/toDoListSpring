package kz.toDoList.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import kz.toDoList.entity.Record;
import kz.toDoList.entity.RecordStatus;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Transactional
public class RecordDao {

    @PersistenceContext
    private  EntityManager entityManager;

    public List<Record> findAll() {
        Query q = entityManager.createQuery("select r from Record r order by r.id asc");
        List<Record> records = q.getResultList();
        return records;

    }


    public void saveRecord(Record record) {
        entityManager.persist(record);
    }

    public void deleteRecord(int id) {
        Query q = entityManager.createQuery("delete from Record r where id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }

    public void updateRecordStatus(int id, RecordStatus newStatus){
        Query q = entityManager.createQuery("update Record set status = :status where id = :id");
        q.setParameter("status", newStatus);
        q.setParameter("id", id);
        q.executeUpdate();
    }
}

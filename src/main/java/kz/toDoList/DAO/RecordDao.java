package kz.toDoList.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import kz.toDoList.entity.Record;
import kz.toDoList.entity.RecordStatus;
import kz.toDoList.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class RecordDao {

    private final EntityManagerFactory entityManagerFactory;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    public RecordDao(EntityManagerFactory entityManagerFactory, HandlerExceptionResolver handlerExceptionResolver) {
        this.entityManagerFactory = entityManagerFactory;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }


    public List<Record> findAll(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Query q = entityManager.createQuery("select r from Record r");
            List<Record> records = q.getResultList();

            entityManager.getTransaction().commit();
            return records;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }
    }


    public void saveRecord(Record record){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            entityManager.persist(record);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    public void deleteRecord(int id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Query q = entityManager.createQuery("delete from Record r where id == :id");
            q.setParameter("id", id);
            q.executeUpdate();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    public void updateRecordStatus(int id, RecordStatus newStatus){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Query q = entityManager.createQuery("update Record set status = :status where id = :id");
            q.setParameter("status", newStatus);
            q.setParameter("id", id);
            q.executeUpdate();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}

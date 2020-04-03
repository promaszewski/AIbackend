package pl.edu.wat.AI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.AI.Model.Entity.VisitEntity;

import java.sql.Time;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<VisitEntity,Integer> {
    VisitEntity findById(int id);
    VisitEntity findByData(Time data);
    List<VisitEntity> findByIduser(int id);
}

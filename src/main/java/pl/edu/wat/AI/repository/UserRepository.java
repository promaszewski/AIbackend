package pl.edu.wat.AI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.AI.Model.Entity.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findById(int id);
    UserEntity findByEmail(String email);
    List<UserEntity> findAll();
    
}

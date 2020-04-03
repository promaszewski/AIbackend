package pl.edu.wat.AI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.AI.Model.Entity.UserEntity;
import pl.edu.wat.AI.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserEntity loadbyusername(String name){
        UserEntity userEntity = userRepository.findByEmail(name);
        userEntity.setPassword("niepowiem");
        return userEntity;
    }
    public List<UserEntity> listuser(){
      List<UserEntity> userEntities = userRepository.findAll();
        for (UserEntity userEntity : userEntities) {
            userEntity.setPassword("");

        }
        return userEntities;
    }

}

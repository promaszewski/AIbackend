package pl.edu.wat.AI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.AI.Model.Dataout.Role;
import pl.edu.wat.AI.Model.Dataout.User;
import pl.edu.wat.AI.repository.UserRepository;
import pl.edu.wat.AI.service.UserDetailsServiceImp;
import pl.edu.wat.AI.Model.Entity.UserEntity;
import pl.edu.wat.AI.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/greet")
public class UserController {
    @Autowired
    UserDetailsServiceImp userservice;
    @Autowired
    UserService us;
    @Autowired
    UserRepository userRepository;
    @RequestMapping( value = "/userlist",method = RequestMethod.GET)
    public List<UserEntity> userlist ()
    {
        return us.listuser();
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public UserEntity adduser(@RequestBody @Validated User user)
    {UserEntity userEntity = new UserEntity();
    userEntity.setPassword(user.getPassword());
    userEntity.setEmail(user.getEmail());
    userEntity.setActive(1);
    userEntity.setRole(user.getRole());

        return userservice.adduser(userEntity);

    }
    @RequestMapping(value = "/role", method = RequestMethod.GET)
            public Role rola(OAuth2Authentication auth)
    {
       UserEntity userEntity= userRepository.findByEmail(auth.getUserAuthentication().getName());
       Role role = new Role();
       role.setRole(userEntity.getRole());
       return role;
    }
}

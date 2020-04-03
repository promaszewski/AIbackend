package pl.edu.wat.AI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.AI.Model.Dataout.Username;
import pl.edu.wat.AI.Model.Entity.UserEntity;
import pl.edu.wat.AI.service.UserDetailsServiceImp;
import pl.edu.wat.AI.service.UserService;

@RestController
@RequestMapping("/greet")
public class GreetingController {
@Autowired
UserService userService;

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public UserEntity greetPerson(OAuth2Authentication auth) {

   UserEntity userEntity = userService.loadbyusername(auth.getUserAuthentication().getName().toString());
   return userEntity;
  }

}

package pl.edu.wat.AI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.AI.Model.Dataout.Visitid;
import pl.edu.wat.AI.Model.Entity.UserEntity;
import pl.edu.wat.AI.Model.Entity.VisitEntity;
import pl.edu.wat.AI.service.UserDetailsServiceImp;
import pl.edu.wat.AI.service.VisitService;

import java.util.List;

@RestController()
@RequestMapping("/greet")
public class VisitController {
    @Autowired
    VisitService visitService;
    @Autowired
    UserDetailsServiceImp userDetailsServiceImp;
    @RequestMapping(value = "/addvisit",method = RequestMethod.POST)
    public VisitEntity add(@RequestBody @Validated VisitEntity visitEntity, OAuth2Authentication auth){
        UserEntity userEntity = userDetailsServiceImp.loaduserbyemail(auth.getUserAuthentication().getName());
        visitEntity.setIduser(userEntity.getId());
        return visitService.addvisit(visitEntity);
    }
    @RequestMapping(value = "/managevisit/{id}", method = RequestMethod.GET)
    public List<VisitEntity> managevisit(@PathVariable Integer id){return visitService.manage(id);}
    @RequestMapping(value = "/myvisit",method = RequestMethod.GET)
    public List<VisitEntity> myvisit(OAuth2Authentication auth){
        UserEntity userEntity = userDetailsServiceImp.loaduserbyemail(auth.getUserAuthentication().getName());
        List<VisitEntity> visitEntity = visitService.myvisit(userEntity.getId());
        System.out.println(visitEntity.toString());
        return visitEntity;
    }

    @RequestMapping(value = "/upvisit",method = RequestMethod.POST)
    public VisitEntity upvisit(@RequestBody @Validated VisitEntity visitEntity){
    return visitService.upvisit(visitEntity);

    }
    @GetMapping(value = "/onevisit/{id}")
    public VisitEntity VisitEntity(@PathVariable Integer id){
        return visitService.one(id);
    }
}

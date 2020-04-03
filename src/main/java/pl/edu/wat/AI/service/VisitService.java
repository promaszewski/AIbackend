package pl.edu.wat.AI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.AI.Model.Entity.VisitEntity;
import pl.edu.wat.AI.repository.VisitRepository;

import java.util.List;

@Service
public class VisitService {
    @Autowired
    VisitRepository visitRepository;

    public VisitEntity addvisit(VisitEntity visitEntity){
        return visitRepository.save(visitEntity);

    }
    public List<VisitEntity> myvisit(int id){
        return visitRepository.findByIduser(id);
    }
    public VisitEntity upvisit(VisitEntity visitEntity){
        return visitRepository.save(visitEntity);
    }
    public VisitEntity one(int id){
        return visitRepository.findById(id);
    }
    public List<VisitEntity> manage(int id){ return visitRepository.findByIduser(id);}
}

package pl.edu.wat.AI.Model.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Table;
import java.sql.Time;

@Data
@Entity
@Table(name="visit")
public class VisitEntity {
    @Id
    private int id;
    private int iduser;
    private String nazwakontrahenta;
    private String misto;
    private String ulica;
    private String nrdomu;
    private String komentarz;
    private String materialyrek;
    private boolean status;
    private java.util.Date data;

}

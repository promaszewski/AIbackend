package pl.edu.wat.AI.Model.Entity;

import lombok.Data;

import javax.persistence.Id;


import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@Entity()

@Table(name="users")
public class UserEntity {
private String email;
private String password;
private String token;
private int active;
private int deleted;
@Id
private int id;
private String role;


}

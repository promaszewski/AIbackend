package pl.edu.wat.AI.Model.Dataout;

import lombok.Data;

@Data
public class User {
    private String email;
    private String password;
    private String active;
    private String role;

}

package pl.edu.wat.AI.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.wat.AI.Model.Entity.UserEntity;
import pl.edu.wat.AI.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserDetailsServiceImp implements UserDetailsService {



    private final UserRepository userRepository;
    public UserDetailsServiceImp(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    /*Here we are using dummy data, you need to load user data from
     database or other third party application*/


        UserEntity user=userRepository.findByEmail(email);
        PasswordEncoder passwordEncoder =PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User user1;
        if (user != null) {
           // builder = org.springframework.security.core.userdetails.User.withUsername(user.getEmail());
            //builder.password(user.getPassword());
            //builder.roles();
            List<GrantedAuthority> authoritiesListe = new ArrayList<GrantedAuthority>();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("USER");
            authoritiesListe.add(simpleGrantedAuthority);
                    user1= new User(user.getEmail(),user.getPassword(),authoritiesListe);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return user1;
    }

    public UserEntity adduser(UserEntity user){
        if(userRepository.findByEmail(user.getEmail()) != null) {throw new UsernameNotFoundException("user exist");}
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            String password = encoder.encode(user.getPassword());
            user.setPassword(password);
            return userRepository.save(user);
        }
        public UserEntity loaduserbyemail(String name){
        return userRepository.findByEmail(name);
        }
    }






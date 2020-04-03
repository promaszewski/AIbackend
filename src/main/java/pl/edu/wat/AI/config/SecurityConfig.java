package pl.edu.wat.AI.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.edu.wat.AI.repository.UserRepository;
import pl.edu.wat.AI.service.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  DataSource dataSource;
  @Autowired
  UserRepository userRepository;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     // PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
      //auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
   /* auth.jdbcAuthentication()
      .dataSource(dataSource)
      .usersByUsernameQuery("SELECT `email`,`password`,`active` FROM `users` WHERE `email`=?")
      .authoritiesByUsernameQuery("SELECT `email`,'USER' FROM `users` WHERE `email`=?")
      .passwordEncoder(passwordEncoder);*/
   auth.userDetailsService(new UserDetailsServiceImp(userRepository)).passwordEncoder(passwordEncoder());

  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
   return PasswordEncoderFactories.createDelegatingPasswordEncoder();  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
        .antMatchers("/actuator/health").permitAll()
        .anyRequest().denyAll()
       .and()
      .formLogin().disable();
  }


}

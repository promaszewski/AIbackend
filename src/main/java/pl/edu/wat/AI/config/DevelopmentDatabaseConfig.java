package pl.edu.wat.AI.config;

import org.springframework.context.annotation.*;

@Configuration

@Profile({ "development", "test" })
public class DevelopmentDatabaseConfig {

 // @Bean
 // @Primary
  //@ConfigurationProperties(prefix="spring.datasource")
  //public DataSource ds() {
  //  return DataSourceBuilder.create().build();
  //}

}

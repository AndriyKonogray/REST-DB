package org.db.REST.DB.configs;

import org.db.REST.DB.interfaces.UserService;
import org.db.REST.DB.services.UserServiceBaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    UserService userService() {
        return new UserServiceBaseImpl();
    }
}

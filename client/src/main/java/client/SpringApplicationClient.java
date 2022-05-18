package client;

import client.entity.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringApplicationClient {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplicationClient.class, args);
    }

    @Bean
    public User createUser() {
        return new User();
    }
}

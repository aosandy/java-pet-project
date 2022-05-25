package client;

import client.entity.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringApplicationClient {

    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = org.springframework.boot.SpringApplication.run(SpringApplicationClient.class, args);
        //displayAllBeans();
    }

    @Bean
    public User user() {
        return new User();
    }

    public static void displayAllBeans() {
        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }
}

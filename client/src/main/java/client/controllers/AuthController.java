package client.controllers;

import client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private User user;

    @GetMapping("/singin")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "auth/singin";
    }

    @PostMapping("/singin")
    public String singIn(@ModelAttribute("user") User userModel){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/auth/singin";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> request = new HttpEntity<>(userModel, httpHeaders);
        try {
            ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, request, User.class);
            User responseUser = responseEntity.getBody();
            this.user.setUsername(responseUser.getUsername());
            this.user.setPassword(responseUser.getPassword());
            this.user.setRoles(responseUser.getRoles());
            this.user.setToken(responseUser.getToken());
            this.user.setId(responseUser.getId());
            return "redirect:/";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "redirect:/error";
        }
    }
}

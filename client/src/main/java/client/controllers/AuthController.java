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

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private User user;

    @GetMapping("/signin")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "auth/signin";
    }

    @PostMapping("/signin")
    public String singIn(@ModelAttribute("user") User userModel, Model model){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/auth/signin";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> request = new HttpEntity<>(userModel, httpHeaders);
        try {
            ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, request, User.class);
            User responseUser = responseEntity.getBody();
            this.user.setUsername(responseUser.getUsername());
            this.user.setRoles(responseUser.getRoles());
            this.user.setToken(responseUser.getToken());
            this.user.setId(responseUser.getId());
            return "redirect:/";
        } catch (Exception exception) {
            exception.printStackTrace();
            model.addAttribute("errorMessage", "Invalid username or password");
            return "auth/signin";
        }
    }

/*
    @GetMapping("/signin-error")
    public String loginError(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", "Invalid username or password");
        model.addAttribute("user", new User());
        return "auth/signin";
    }
*/
}

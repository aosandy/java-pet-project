package client.controllers;

import client.entity.Auto;
import client.entity.Route;
import client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/autos")
public class AutoController {

    @Autowired
    private User user;

    @GetMapping()
    public String allAutos(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/autos/";

        ResponseEntity<Auto[]> responseEntity = restTemplate.getForEntity(url, Auto[].class);
        model.addAttribute("autos", responseEntity.getBody());

        return "auto/autos";
    }

    @GetMapping("/{id}")
    public String getAuto(@PathVariable("id") Long id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/autos/" + id;

        ResponseEntity<Auto> responseEntity = restTemplate.getForEntity(url, Auto.class);
        model.addAttribute("auto", responseEntity.getBody());

        return "auto/auto";
    }
    @GetMapping("/create")
    public String newAuto(Model model) {
        model.addAttribute("auto", new Auto());

        return "auto/auto-create";
    }

    @PostMapping("/create")
    public String createAuto(@ModelAttribute("auto") Auto auto) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/autos/";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer " + user.getToken());
        HttpEntity<Auto> request = new HttpEntity<>(auto, httpHeaders);
        try {
            ResponseEntity<Auto> responseEntity = restTemplate.postForEntity(url, request, Auto.class);
            return "redirect:/autos";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "redirect:/autos";
        }
    }

    @GetMapping("/{id}/edit")
    public String editAuto(@PathVariable("id") Integer id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/autos/" + id;
        ResponseEntity<Auto> responseEntity = restTemplate.getForEntity(url, Auto.class);
        model.addAttribute("auto", responseEntity.getBody());
        return "auto/auto-edit";
    }

    @PutMapping("/{id}/edit")
    public String patchAuto(@PathVariable("id") Long id, @ModelAttribute("auto") Auto auto) {
        auto.setId(id);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/routes/" + id;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer " + user.getToken());
        HttpEntity<Auto> request = new HttpEntity<>(auto, httpHeaders);
        try {
            restTemplate.exchange(url, HttpMethod.PUT, request, Auto.class);
        } catch (Exception ignored) {
            return "redirect:/autos";
        }
        return "redirect:/autos";
    }

    @DeleteMapping("/{id}")
    public String deleteAuto(@PathVariable("id") Long id) {
        Auto auto = new Auto();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/autos/" + id;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer " + user.getToken());
        HttpEntity<Auto> request = new HttpEntity<>(auto, httpHeaders);
        try {
            restTemplate.exchange(url, HttpMethod.DELETE, request, Auto.class);
        } catch (Exception ignored) {
            return "redirect:/autos";
        }
        return "redirect:/autos";
    }
}

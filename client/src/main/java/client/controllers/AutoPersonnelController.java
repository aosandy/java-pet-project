package client.controllers;

import client.entity.AutoPersonnel;
import client.entity.Route;
import client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/personnels")
public class AutoPersonnelController {

    @Autowired
    private User user;

    @GetMapping()
    public String allAutoPersonnels(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/personnels/";

        ResponseEntity<AutoPersonnel[]> responseEntity = restTemplate.getForEntity(url, AutoPersonnel[].class);
        model.addAttribute("personnels", responseEntity.getBody());

        return "personnel/personnels";
    }

    @GetMapping("/{id}")
    public String getAutoPersonnel(@PathVariable("id") Long id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/personnels/" + id;

        ResponseEntity<AutoPersonnel> responseEntity = restTemplate.getForEntity(url, AutoPersonnel.class);
        model.addAttribute("personnel", responseEntity.getBody());

        return "personnel/personnel";
    }

    @GetMapping("/create")
    public String newAutoPersonnel(Model model) {
        model.addAttribute("personnel", new AutoPersonnel());

        return "personnel/personnel-create";
    }

    @PostMapping("/create")
    public String createAutoPersonnel(@ModelAttribute("personnel") AutoPersonnel personnel) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/personnels/";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer " + user.getToken());
        HttpEntity<AutoPersonnel> request = new HttpEntity<>(personnel, httpHeaders);
        try {
            ResponseEntity<AutoPersonnel> responseEntity = restTemplate.postForEntity(url, request, AutoPersonnel.class);
            return "redirect:/personnels";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "redirect:/personnels";
        }
    }

    @GetMapping("/{id}/edit")
    public String editAutoPersonnel(@PathVariable("id") Integer id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/personnels/" + id;
        ResponseEntity<Route> responseEntity = restTemplate.getForEntity(url, Route.class);
        model.addAttribute("personnel", responseEntity.getBody());
        return "personnel/personnel-edit";
    }

    @PutMapping("/{id}/edit")
    public String patchAutoPersonnel(@PathVariable("id") Long id, @ModelAttribute("personnel") AutoPersonnel personnel) {
        personnel.setId(id);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/personnels/" + id;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer " + user.getToken());
        HttpEntity<AutoPersonnel> request = new HttpEntity<>(personnel, httpHeaders);
        try {
            restTemplate.exchange(url, HttpMethod.PUT, request, AutoPersonnel.class);
        } catch (Exception ignored) {
            return "redirect:/personnels";
        }
        return "redirect:/personnels";
    }

    @DeleteMapping("/{id}")
    public String deleteAutoPersonnel(@PathVariable("id") Long id) {
        AutoPersonnel personnel = new AutoPersonnel();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/personnels/" + id;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer " + user.getToken());
        HttpEntity<AutoPersonnel> request = new HttpEntity<>(personnel, httpHeaders);
        try {
            restTemplate.exchange(url, HttpMethod.DELETE, request, AutoPersonnel.class);
        } catch (Exception ignored) {
            return "redirect:/personnels";
        }
        return "redirect:/personnels";
    }
}

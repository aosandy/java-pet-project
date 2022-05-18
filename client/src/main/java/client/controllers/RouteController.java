package client.controllers;

import client.entity.Route;
import client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private User user;

    @GetMapping()
    public String allRoutes(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/routes/";

        ResponseEntity<Route[]> responseEntity = restTemplate.getForEntity(url, Route[].class);
        model.addAttribute("routes", responseEntity.getBody());

        return "route/routes";
    }

    @GetMapping("/{id}")
    public String getRoute(@PathVariable("id") Long id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/routes/" + id;

        ResponseEntity<Route> responseEntity = restTemplate.getForEntity(url, Route.class);
        model.addAttribute("route", responseEntity.getBody());

        return "route/route";
    }

    @GetMapping("/create")
    public String newRoute(Model model) {
        model.addAttribute("route", new Route());

        return "route/route-create";
    }

    @PostMapping("/create")
    public String createRoute(@ModelAttribute("route") Route route) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/routes/";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer " + user.getToken());
        HttpEntity<Route> request = new HttpEntity<>(route, httpHeaders);
        try {
            ResponseEntity<Route> responseEntity = restTemplate.postForEntity(url, request, Route.class);
            return "redirect:/routes";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "redirect:/routes";
        }
    }

    @GetMapping("/{id}/edit")
    public String editRoute(@PathVariable("id") Integer id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/routes/" + id;
        ResponseEntity<Route> responseEntity = restTemplate.getForEntity(url, Route.class);
        model.addAttribute("route", responseEntity.getBody());
        return "route/route-edit";
    }

    @PutMapping("/{id}/edit")
    public String patchRoute(@PathVariable("id") Long id, @ModelAttribute("route") Route route) {
        route.setId(id);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/routes/" + id;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer " + user.getToken());
        HttpEntity<Route> request = new HttpEntity<>(route, httpHeaders);
        try {
            restTemplate.exchange(url, HttpMethod.PUT, request, Route.class);
        } catch (Exception ignored) {
            return "redirect:/routes";
        }
        return "redirect:/routes";
    }

    @DeleteMapping("/{id}")
    public String deleteRoute(@PathVariable("id") Long id) {
        Route route = new Route();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/routes/" + id;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer " + user.getToken());
        HttpEntity<Route> request = new HttpEntity<>(route, httpHeaders);
        try {
            restTemplate.exchange(url, HttpMethod.DELETE, request, Route.class);
        } catch (Exception ignored) {
            return "redirect:/routes";
        }
        return "redirect:/routes";
    }
}

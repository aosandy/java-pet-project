package client.controllers;

import client.entity.Journal;
import client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    private User user;

    @GetMapping()
    public String allJournals(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/journals/";

        ResponseEntity<Journal[]> responseEntity = restTemplate.getForEntity(url, Journal[].class);
        model.addAttribute("journals", responseEntity.getBody());

        return "journal/journals";
    }

    @GetMapping("/{id}")
    public String getJournal(@PathVariable("id") Long id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/journals/" + id;

        ResponseEntity<Journal> responseEntity = restTemplate.getForEntity(url, Journal.class);
        model.addAttribute("journal", responseEntity.getBody());

        return "journal/journal";
    }

    @GetMapping("/create")
    public String newJournal(Model model) {
        model.addAttribute("journal", new Journal());

        return "journal/journal-create";
    }

    @PostMapping("/create")
    public String createJournal(@ModelAttribute("journal") Journal journal) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/journals/";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer " + user.getToken());
        HttpEntity<Journal> request = new HttpEntity<>(journal, httpHeaders);
        try {
            ResponseEntity<Journal> responseEntity = restTemplate.postForEntity(url, request, Journal.class);
            return "redirect:/journals";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "redirect:/journals";
        }
    }

    @GetMapping("/{id}/edit")
    public String editJournal(@PathVariable("id") Integer id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/journals/" + id;
        ResponseEntity<Journal> responseEntity = restTemplate.getForEntity(url, Journal.class);
        model.addAttribute("journal", responseEntity.getBody());
        return "journal/journal-edit";
    }

    @PutMapping("/{id}/edit")
    public String patchJournal(@PathVariable("id") Long id, @ModelAttribute("journal") Journal journal) {
        journal.setId(id);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/journals/" + id;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer " + user.getToken());
        HttpEntity<Journal> request = new HttpEntity<>(journal, httpHeaders);
        try {
            restTemplate.exchange(url, HttpMethod.PUT, request, Journal.class);
        } catch (Exception ignored) {
            return "redirect:/journals";
        }
        return "redirect:/journals";
    }

    @DeleteMapping("/{id}")
    public String deleteJournal(@PathVariable("id") Long id) {
        Journal journal = new Journal();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/journals/" + id;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer " + user.getToken());
        HttpEntity<Journal> request = new HttpEntity<>(journal, httpHeaders);
        try {
            restTemplate.exchange(url, HttpMethod.DELETE, request, Journal.class);
        } catch (Exception ignored) {
            return "redirect:/journals";
        }
        return "redirect:/journals";
    }
}

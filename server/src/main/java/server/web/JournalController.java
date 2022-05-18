package server.web;

import server.entity.Journal;
import server.exception.EntityNotFoundException;
import server.service.AutoService;
import server.service.JournalService;
import server.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private AutoService autoService;

    @PostMapping()
    public ResponseEntity<Journal> createJournal(@RequestBody Journal newJournal) {
        return new ResponseEntity<>(journalService.createJournal(newJournal), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Journal> updateJournal(@PathVariable("id") Long id, @RequestBody Journal newJournal) {
        return new ResponseEntity<>(journalService.updateJournal(id, newJournal), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteJournal(@PathVariable("id") Long id) {
        journalService.deleteJournal(id);
    }

    @GetMapping()
    public ResponseEntity<List<Journal>> allJournals() {
        List<Journal> list = journalService.allJournals();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Journal> getJournal(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(journalService.findJournal(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Journal not found");
        }
    }

    @GetMapping("/existsByRoute/{routeId}")
    public boolean existsByRoute(@PathVariable("routeId") Long routeId) {
        return journalService.existsByRouteId(routeService.findRoute(routeId));
    }

    @GetMapping("/existsByAuto/{autoId}")
    public boolean existsByAuto(@PathVariable("autoId") Long autoId) {
        return journalService.existsByAutoId(autoService.findAuto(autoId));
    }

    @GetMapping("/countByRoute/{routeId}")
    public Long countByRoute(@PathVariable("routeId") Long routeId) {
        return journalService.countByRouteId(routeService.findRoute(routeId));
    }
}

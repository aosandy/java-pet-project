package server.web;

import server.entity.Route;
import server.exception.EntityNotFoundException;
import server.service.JournalService;
import server.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private JournalService journalService;

    @PostMapping()
    public ResponseEntity<Route> createRoute(@RequestBody Route newRoute) {
        return new ResponseEntity<>(routeService.createRoute(newRoute), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable("id") Long id, @RequestBody Route newRoute) {
        return new ResponseEntity<>(routeService.updateRoute(id, newRoute), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteRoute(@PathVariable("id") Long id) {
        routeService.deleteRoute(id);
    }

    @GetMapping()
    public ResponseEntity<List<Route>> allRoutes() {
        List<Route> list = routeService.allRoutes();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Route> getRoute(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(routeService.findRoute(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Route not found");
        }
    }

    @DeleteMapping("/deleteByJournal/{journalId}")
    public void deleteByJournal(@PathVariable("journalId") Long journalId) {
        routeService.deleteByJournal(journalService.findJournal(journalId));
    }
}

package server.web;

import server.entity.Auto;
import server.entity.AutoPersonnel;
import server.exception.EntityNotFoundException;
import server.service.AutoPersonnelService;
import server.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/autos")
public class AutoController {

    @Autowired
    private AutoService autoService;

    @Autowired
    private AutoPersonnelService autoPersonnelService;

    @PostMapping()
    public ResponseEntity<Auto> createAuto(@RequestBody Auto newAuto) {
        return new ResponseEntity<>(autoService.createAuto(newAuto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auto> updateAuto(@PathVariable("id") Long id, @RequestBody Auto newAuto) {
        return new ResponseEntity<>(autoService.updateAuto(id, newAuto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteAuto(@PathVariable("id") Long id) {autoService.deleteAuto(id);
    }

    @GetMapping()
    public ResponseEntity<List<Auto>> allAutos() {
        List<Auto> list = autoService.allAutos();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auto> getAuto(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(autoService.findAuto(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auto not found");
        }
    }

    @GetMapping("/findByPersonnel/{personnelId}")
    public ResponseEntity<List<Auto>> findByPersonnelId(@PathVariable("personnelId") Long personnelId) {
        return new ResponseEntity<>(autoService.findByPersonnelId(autoPersonnelService.findAutoPersonnel(personnelId)), HttpStatus.OK);
    }

    @PutMapping("/{id}/personnel")
    public int updatePersonnelById(@PathVariable("id") Long id, @RequestBody AutoPersonnel personnelId) {
        return autoService.updatePersonnelById(id, personnelId);
    }
}

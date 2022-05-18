package server.web;

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
@RequestMapping("/personnels")
public class AutoPersonnelController {

    @Autowired
    private AutoPersonnelService autoPersonnelService;

    @Autowired
    private AutoService autoService;

    @PostMapping()
    public ResponseEntity<AutoPersonnel> createAuto(@RequestBody AutoPersonnel newAutoPersonnel) {
        return new ResponseEntity<>(autoPersonnelService.createAutoPersonnel(newAutoPersonnel), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutoPersonnel> updateAuto(@PathVariable("id") Long id, @RequestBody AutoPersonnel newAuto) {
        return new ResponseEntity<>(autoPersonnelService.updateAutoPersonnel(id, newAuto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteAuto(@PathVariable("id") Long id) {autoPersonnelService.deleteAutoPersonnel(id);
    }

    @GetMapping()
    public ResponseEntity<List<AutoPersonnel>> allAutoPersonnels() {
        List<AutoPersonnel> list = autoPersonnelService.allAutoPersonnels();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoPersonnel> getAutoPersonnel(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(autoPersonnelService.findAutoPersonnel(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AutoPersonnel not found");
        }
    }

    @GetMapping("/findByAuto/{autoId}")
    public ResponseEntity<AutoPersonnel> findByAuto(@PathVariable("autoId") Long autoId) {
        return new ResponseEntity<>(autoPersonnelService.findByAuto(autoService.findAuto(autoId)), HttpStatus.OK);
    }
}

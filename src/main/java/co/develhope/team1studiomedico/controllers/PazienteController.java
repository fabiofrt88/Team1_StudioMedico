package co.develhope.team1studiomedico.controllers;

import co.develhope.team1studiomedico.entities.Paziente;
import co.develhope.team1studiomedico.services.PazienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paziente")
public class PazienteController {

    @Autowired
    private PazienteService pazienteService;

    @PostMapping("/create")
    public ResponseEntity<String> createPaziente(@RequestBody Paziente paziente){
        if(paziente == null) throw new IllegalArgumentException();
        pazienteService.createPaziente(paziente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Paziente creato correttamente");
    }

    @GetMapping({"", "/"})
    public List<Paziente> getPazienti(){
        return pazienteService.getPazienti();
    }

    @GetMapping("/{id}")
    public Paziente getPazienteById(@PathVariable Long id) {
        /*Optional<Paziente> paziente = pazienteService.getPazienteById(id);
        if(paziente.isPresent()) {
            return paziente.get();
        }
        return new Paziente();*/
        if(id == null) throw new IllegalArgumentException();
        return pazienteService.getPazienteById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> updatePazienteById(@RequestBody Paziente pazienteEdit, @PathVariable Long id) {
        if(pazienteEdit == null || id == null) throw new IllegalArgumentException();
        pazienteService.updatePazienteById(pazienteEdit, id);
        return ResponseEntity.status(200).body("Paziente modificato correttamente");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePazienti() {
        pazienteService.deletePazienti();
        return ResponseEntity.status(200).body("Pazienti cancellati correttamente");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePazienteById(@PathVariable Long id) {
        if(id == null) throw new IllegalArgumentException();
        pazienteService.deletePazienteById(id);
        return ResponseEntity.status(200).body("Paziente cancellato correttamente");
    }

}

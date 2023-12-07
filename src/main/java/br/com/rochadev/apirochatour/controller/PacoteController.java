package br.com.rochadev.apirochatour.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rochadev.apirochatour.models.PacoteModel;

import br.com.rochadev.apirochatour.services.PacoteService;


@RestController
@RequestMapping("/pacote")
public class PacoteController {

  /**
   * passando o serviço como atributo e injetando através do construtor do PacoteController
   */
  private final PacoteService pacoteService;

  public PacoteController(PacoteService pacoteService) {
        this.pacoteService = pacoteService;
  }
  
  @PostMapping
    public ResponseEntity<PacoteModel> createPacote(@RequestBody PacoteModel pacote) {
        PacoteModel createdPacote = pacoteService.createPacote(pacote);
        return new ResponseEntity<>(createdPacote, HttpStatus.CREATED);
    }

  @GetMapping
  public ResponseEntity<List<PacoteModel>> getAllPacotes() {
      List<PacoteModel> pacotes = pacoteService.getAllPacotes();
      return new ResponseEntity<>(pacotes, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePacote(@PathVariable("id") String pacoteId) {
        pacoteService.deletePacoteById(pacoteId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

     @PutMapping("/{id}")
    public ResponseEntity<PacoteModel> updatePacote(@PathVariable("id") String pacoteId, @RequestBody PacoteModel updatedPacote) {
        updatedPacote.setId(pacoteId);
        PacoteModel updated = pacoteService.updatePacote(updatedPacote);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}

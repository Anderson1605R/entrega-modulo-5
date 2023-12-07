package br.com.rochadev.apirochatour.services;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import br.com.rochadev.apirochatour.models.PacoteModel;

import br.com.rochadev.apirochatour.repositories.IPacotesRepositories;


@Service
public class PacoteService {
  private final IPacotesRepositories pacoteRepo;

  public PacoteService(IPacotesRepositories pacoteRepo) {
        this.pacoteRepo = pacoteRepo;
    }

  public PacoteModel createPacote(PacoteModel pacote){
    if (pacote == null) {
      throw new IllegalArgumentException("O usuário não pode ser nulo");
  }
  return pacoteRepo.save(pacote);
  }

  public List<PacoteModel> getAllPacotes() {
        return pacoteRepo.findAll();
    }
  
    public void deletePacoteById(String pacoteId) {
      if (pacoteId == null) {
          throw new IllegalArgumentException("ID do usuário não pode ser nulo ou vazio");
      }
      pacoteRepo.deleteById(pacoteId);
    }


    public PacoteModel updatePacote(PacoteModel updatedPacote) {
        if (updatedPacote == null || updatedPacote.getId() == null) {
            throw new IllegalArgumentException("Usuário ou ID inválido para atualização");
        }

        Optional<PacoteModel> existingUser = pacoteRepo.findById(updatedPacote.getId());
        if (existingUser.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado para atualização");
        }

        return pacoteRepo.save(updatedPacote);
    }

}
